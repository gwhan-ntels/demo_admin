package com.ntels.ccbs.charge.service.common;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.domain.common.AmbgOcc;
import com.ntels.ccbs.charge.mapper.common.AmbgMapper;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class AmbgServiceImpl implements AmbgService {

//	@Autowired
//	private ClogService clogService;

	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private PyCommService pyCommService;
	@Autowired
	private AmbgMapper ambgMapper;

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
		String ambgOccSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.BL_AMBG_NO, 10);
		ambgOcc.setAmbgOccSeqNo(ambgOccSeqNo);
		List<AmbgOcc> ambgOccList = new ArrayList<>();
		ambgOccList.add(ambgOcc);
		
		return ambgMapper.insertAmbgOcc(ambgOcc);
	}
	
}
