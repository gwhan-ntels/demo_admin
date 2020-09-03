package com.ntels.ccbs.charge.service.common;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.ntels.ccbs.charge.domain.common.AmbgOcc;
import com.ntels.ccbs.charge.mapper.common.AmbgMapper;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class AmbgServiceImpl implements AmbgService {
	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private PyCommService pyCommService;
	@Autowired
	private AmbgMapper ambgMapper;
	

	@Override
	public int insertAmbgOcc(String ambgOccSeqNo,  String regrId, String dpstSeqNo) {
		AmbgOcc ambgOcc = ambgMapper.getAmbgOcc(dpstSeqNo);

		ambgOcc.setAmbgOccSeqNo(ambgOccSeqNo);
		ambgOcc.setAmbgOccDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		ambgOcc.setAmbgOccTp("01");
		ambgOcc.setAmbgOccResn("1");
		ambgOcc.setDpstTpSeqNo(dpstSeqNo);
		ambgOcc.setDpstProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		ambgOcc.setAmbgProcStat("1");
		ambgOcc.setAmbgTransAmt(0.0);
		ambgOcc.setTransCmplYn("N");
		ambgOcc.setRegrId(regrId);
		ambgOcc.setRegDate(new Timestamp(new Date().getTime()));
		ambgOcc.setCnclYn("N");
		ambgOcc.setCnclDttm("");
		
		return ambgMapper.insertAmbgOcc(ambgOcc);
	}

	@Override
	public int insertAmbgOcc(String regrId, String dpstSeqNo) {
		AmbgOcc ambgOcc = ambgMapper.getAmbgOcc(dpstSeqNo);

		String ambgOccDttm = DateUtil.getDateStringYYYYMMDDHH24MISS(0);
		
		ambgOcc.setAmbgOccDttm(ambgOccDttm);
		ambgOcc.setAmbgOccTp("01");
		ambgOcc.setAmbgOccResn("1");
		ambgOcc.setDpstTpSeqNo(dpstSeqNo);
		ambgOcc.setDpstProcDttm(ambgOccDttm);
		ambgOcc.setAmbgProcStat("1");
		ambgOcc.setAmbgTransAmt(0.0);
		ambgOcc.setTransCmplYn("N");
		ambgOcc.setRegrId(regrId);
		ambgOcc.setRegDate(new Timestamp(new Date().getTime()));
		ambgOcc.setCnclYn("N");
		ambgOcc.setCnclDttm("");
		
		return insertAmbgOcc(ambgOcc);
	}
	
	@Override
	public int insertAmbgOcc(AmbgOcc ambgOcc) {
		String ambgOccSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.MOD_ID_AMBG_OCC, 10);
		ambgOcc.setAmbgOccSeqNo(ambgOccSeqNo);
		List<AmbgOcc> ambgOccList = new ArrayList<>();
		ambgOccList.add(ambgOcc);
		
		return ambgMapper.insertAmbgOcc(ambgOcc);
	}
	
	@Override
	public int updateAmbgCancel(String dpstSeqNo) {
		int count = ambgMapper.getAmbgCount(dpstSeqNo);

		if (count > 0) {
			throw new RuntimeException("[updateAmbg]미확인발생내역에 있으면서 미확인대체내역에 있으면 처리하면 안된다.");
		}

		String cnclDttm = DateUtil.getDateStringYYYYMMDDHH24MISS(0);

		return ambgMapper.updateAmbgCancel(cnclDttm, dpstSeqNo);
	}
	

	/**
	 * 불명금대체이력및 불명금발생내역를 수정한다.
	 * @param dpstSeqNo
	 * @param ambgTransSeqNo
	 * @param payObjAmt
	 * @return
	 */
	@Override
	public int updateAmbg(String dpstSeqNo, String ambgTransSeqNo, double payObjAmt) {
		if (StringUtils.isEmptyOrWhitespaceOnly(dpstSeqNo) == true) {
			throw new RuntimeException("dpstSeqNo또는 pymSeqNo값이 없어서 조회할 수 없습니다.");
		}
		if (StringUtils.isEmptyOrWhitespaceOnly(ambgTransSeqNo) == true) {
			throw new RuntimeException("ambgTransSeqNo값이 없어서 조회할 수 없습니다.");
		}
		
		ambgMapper.updateAmbgTransHistCancel(DateUtil.getDateStringYYYYMMDDHH24MISS(0), ambgTransSeqNo);
		ambgMapper.updateAmbgOccStat(payObjAmt, new Timestamp(new Date().getTime()), dpstSeqNo);
		
		AmbgOcc ambgAmount = ambgMapper.getAmbgAmount(dpstSeqNo);
		
	    if( ambgAmount.getAmbgOccAmt() < ambgAmount.getAmbgBal()) {
	    	logger.debug("데이타보정이 필요한 데이타 입니다.!!!문의");
	    	logger.debug(String.format("ambgOccAmt = [%f]", ambgAmount.getAmbgOccAmt()));
	    	logger.debug(String.format("ambgTransAmt = [%f]", ambgAmount.getAmbgTransAmt()));
	    	logger.debug(String.format("ambgBal = [%f]", ambgAmount.getAmbgBal()));
	    	// TODO 데이터정합성 에러.. 처리 필요
	    }    
		
		// TODO 성공실패 여부 돌려주기
		return 0;
	}
	
}
