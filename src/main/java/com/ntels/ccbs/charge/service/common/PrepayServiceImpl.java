package com.ntels.ccbs.charge.service.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.ntels.ccbs.charge.domain.common.ExrateInfo;
import com.ntels.ccbs.charge.domain.common.PrepayOcc;
import com.ntels.ccbs.charge.domain.common.PrepayTransHistory;
import com.ntels.ccbs.charge.mapper.common.PrepayMapper;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class PrepayServiceImpl implements PrepayService {
	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PrepayMapper prepayMapper;

	@Autowired
	private SequenceService sequenceService;

	@Autowired
	private PyCommService pyCommService;

	@Override
	public int insertPrepayOcc(PrepayOcc prepayOcc) {
		return prepayMapper.insertPrepayOcc(prepayOcc);
	}

	/**
	 * 선수금대체이력및 선수금발생내역를 수정한다.
	 * @param pymSeqNo
	 * @return
	 */
	@Override
	public int updatePrepayOccCancel(String pymSeqNo, String chgrId) {

		if (StringUtils.isEmptyOrWhitespaceOnly(pymSeqNo) == true) {
			throw new RuntimeException("pymSeqNo값이 없어서 조회할 수 없습니다.");
		}

		int count = prepayMapper.getPrepayCount(pymSeqNo);

		// 선수금발생내역에 있으면서 선수금대체내역에 있으면 처리할 수 없음
		if (count > 0) {
			throw new RuntimeException("count is greater than 0.");
		}

		String cnclDttm = DateUtil.getDateStringYYYYMMDDHH24MISS(0);
		Timestamp chgDate = new Timestamp(new Date().getTime());

		return prepayMapper.updatePrepayOccCancel(cnclDttm, pymSeqNo, chgrId, chgDate);

	}

	/**
	 * 선수금대체이력및 선수금발생내역를 수정한다.
	 * 
	 * @param pymSeqNo
	 * @param prepayTransSeqNo
	 * @param payObjAmt
	 * @return
	 */
	@Override
	public int updatePrepayTransHistory(String pymSeqNo, String prepayTransSeqNo, double payObjAmt, String chgrId) {
		if (StringUtils.isEmptyOrWhitespaceOnly(pymSeqNo) == true) {
			throw new RuntimeException("pymSeqNo값이 없어서 조회할 수 없습니다.");
		}

		if (StringUtils.isEmptyOrWhitespaceOnly(prepayTransSeqNo) == true) {
			throw new RuntimeException("prepayTransSeqNo값이 없어서 조회할 수 없습니다.");
		}
		
		// TBLPY_PREPAY_TRANS_HIST cncl_yn. cncl_dttm. proc_memo update
		Timestamp chgDate = new Timestamp(new Date().getTime());
		int check = prepayMapper.updatePrepayTransHistoryCancel(prepayTransSeqNo, chgrId, chgDate);

		if (check < 0) {
			// TODO 갱신된 항목이 없다.. 어떻게 할 것인가?? 지금은 아무것도 하지 않음
		}

		String prepayOccSeqNo = prepayMapper.getPrepayOccSeqNoFromPrepayTransHistory(prepayTransSeqNo);
		check = prepayMapper.updatePrepayTransStat(payObjAmt, prepayOccSeqNo, chgrId, chgDate);

		if (check < 0) {
			// TODO 갱신된 항목이 없다.. 어떻게 할 것인가?? 지금은 아무것도 하지 않음
		}

		PrepayOcc checkPrepayOcc = prepayMapper.getPrepayAmount(prepayOccSeqNo);
		
//		if (checkPrepayOcc.getPrepayOccAmt() < checkPrepayOcc.getPrepayBal()) {
//	        clogService.writeLog("Correcting data is required.");
//	        clogService.writeLog(String.format("prepayOccAmt = [%f]", checkPrepayOcc.getPrepayOccAmt()));
//	        clogService.writeLog(String.format("prepayTransAmt = [%f]", checkPrepayOcc.getPrepayTransAmt()));
//	        clogService.writeLog(String.format("prepayBal = [%f]", checkPrepayOcc.getPrepayBal()));
//	        // TODO 여기는 오류임 처리요망!!
//		}

		// TODO 완료 결과를 어떻게 돌려줄것인지 고민이 필요함.
		return 0;
	}

	/**
	 * 선수금 대체금액 및 선수금 잔액을 조회한다.
	 * 
	 * @param prepayOccSeqNo
	 * @return
	 */
	@Override
	public PrepayOcc getPrepayAmount(String prepayOccSeqNo) {
		if (StringUtils.isEmptyOrWhitespaceOnly(prepayOccSeqNo) == true) {
			logger.debug("prepayOccSeqNo has not be empty");
			throw new RuntimeException("prepayOccSeqNo has not be empty");
		}

		return prepayMapper.getPrepayAmount(prepayOccSeqNo);
	}

	@Override
	public List<String> getBillSeqNoCheck(String billSeqNo) {
		return prepayMapper.getBillSeqNoCheck(billSeqNo);
	}

	/**
	 * 선수금 발생이력 조회
	 * 
	 * @param prepayOccSeqNo
	 * @return
	 */
	@Override
	public PrepayOcc getPrepayOcc(String prepayOccSeqNo) {
		return prepayMapper.getPrepayOcc(prepayOccSeqNo);
	}

	/**
	 * 선수금발생내역을 수정한다.
	 * 
	 * @param prepayOccSeqNo
	 * @param payAplyAmt
	 * @return
	 */
	@Override
	public int updatePrepayOcc(PrepayOcc prepayOcc) {

		String prepayProcStat = prepayMapper.getPrepayProcStat(prepayOcc.getPrepayOccSeqNo());

		if ("2".equals(prepayProcStat) == true) {
			logger.debug("Could not transfer because operation is in applying.(chPrePayProcStat::'2'");
		}

		PrepayOcc updatePrepayOcc = new PrepayOcc();

		updatePrepayOcc.setPrepayOccSeqNo(prepayOcc.getPrepayOccSeqNo());
		updatePrepayOcc.setPrepayBal(prepayOcc.getPrepayBal());
		updatePrepayOcc.setChgrId(prepayOcc.getRegrId());
		updatePrepayOcc.setChgDate(new Timestamp(new Date().getTime()));

		return prepayMapper.updatePrepayOcc(updatePrepayOcc);

	}

	/**
	 * 선수금대체이력에 등록한다.
	 * 
	 * @param prepayOccSeqNo
	 * @param payAplyAmt
	 * @param prepayReplTp
	 * @param regrId
	 * @return
	 */
	@Override
	public String insertPrepayTransHistory(PrepayOcc prepayOcc) {
		String prepayTransSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.MOD_ID_PREPAY_TRANS, 10);

		ExrateInfo exrateInfo = pyCommService.getExrateInfo();
		PrepayOcc prepayBal = getPrepayAmount(prepayOcc.getPrepayOccSeqNo());

		double balAmt = prepayBal.getPrepayOccAmt() - prepayBal.getPrepayTransAmt();

		PrepayTransHistory prepayTransHistory = new PrepayTransHistory();

		prepayTransHistory.setSoId(prepayOcc.getSoId());
		prepayTransHistory.setPrepayOccSeqNo(prepayOcc.getPrepayOccSeqNo());
		prepayTransHistory.setPrepayTransSeqNo(prepayTransSeqNo);
		prepayTransHistory.setTransProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		prepayTransHistory.setPrepayReplTp(prepayOcc.getPrepayOccTp()); // 02, 03
		prepayTransHistory.setTransProcAmt(prepayOcc.getPrepayBal());
		prepayTransHistory.setProcMemo("Advance payment process");
		prepayTransHistory.setWonReplProcAmt(prepayOcc.getPrepayBal());
		prepayTransHistory.setCrncyCd(exrateInfo.getCrncyCd());
		prepayTransHistory.setExrate(exrateInfo.getExrate());
		prepayTransHistory.setExrateAplyDt(exrateInfo.getExrateAplyDt());
		prepayTransHistory.setApprReqrId(prepayOcc.getRegrId());
		prepayTransHistory.setApprReqDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		prepayTransHistory.setDcsnProcStat("01");
		prepayTransHistory.setCnclYn("N");
		prepayTransHistory.setCnclDttm("");
		prepayTransHistory.setBalAmt(balAmt);
		prepayTransHistory.setRegrId(prepayOcc.getRegrId());
		prepayTransHistory.setRegDate(new Timestamp(new Date().getTime()));
		prepayTransHistory.setChgrId(prepayOcc.getRegrId());
		prepayTransHistory.setChgDate(new Timestamp(new Date().getTime()));

		prepayMapper.insertPrepayTransHistory(prepayTransHistory);

		return prepayTransSeqNo;
	}

	/**
	 * 기존의 선수금발생 내역의 데이터를 가지고 새로운 선수금 발생내역을 등록한다.
	 * 
	 * @param prepayOccSeqNo
	 * @return
	 */
	@Override
	public String insertNewPrepayOccFromPrepayOcc(PrepayOcc prepayOcc) {
		String newPrepayOccSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.MOD_ID_PREPAY_OCC, 10);

		PrepayOcc oldPrepayOcc = getPrepayOcc(prepayOcc.getPrepayOccSeqNo());
		PrepayOcc newPrepayOcc = new PrepayOcc();
		copyObjectValue(oldPrepayOcc, newPrepayOcc);

		newPrepayOcc.setPrepayOccSeqNo(newPrepayOccSeqNo);
		// newPrepayOcc.setPymAcntId(prepayOcc.getPymAcntId());
		newPrepayOcc.setPrepayOccDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		newPrepayOcc.setPrepayOccTp("1");
		newPrepayOcc.setPrepayOccResn("1");
		newPrepayOcc.setPrepayOccTgtSeqNo(prepayOcc.getPrepayOccTgtSeqNo());
		newPrepayOcc.setDpstProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		newPrepayOcc.setPrepayProcStat("1");
		newPrepayOcc.setPrepayOccAmt(prepayOcc.getPrepayBal());
		newPrepayOcc.setPrepayTransAmt(0.0);
		newPrepayOcc.setPrepayBal(prepayOcc.getPrepayBal());
		newPrepayOcc.setTransCmplYn("N");
		newPrepayOcc.setWonPrepayOccAmt(0.0);
		newPrepayOcc.setCnclYn("N");
		newPrepayOcc.setCnclDttm("");
		// newPrepayOcc.setTransDt(prepayOcc.getTransDt());
		newPrepayOcc.setRegrId(prepayOcc.getRegrId());
		newPrepayOcc.setRegDate(new Timestamp(new Date().getTime()));
		newPrepayOcc.setChgrId(prepayOcc.getRegrId());
		newPrepayOcc.setChgDate(new Timestamp(new Date().getTime()));

		prepayMapper.insertPrepayOcc(newPrepayOcc);

		return newPrepayOccSeqNo;
	}

	public static void copyObjectValue(Object src, Object dest) {
		Field[] bFields = dest.getClass().getDeclaredFields();

		for (Field field : bFields) {
			String name = field.getName();
			// name = name.substring(0, 1).toUpperCase() + name.substring(1,
			// name.length());

			if (Character.isUpperCase(name.charAt(1))) {
				name = name.substring(0);
			} else {
				name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
			}

			try {
				Method get = src.getClass().getDeclaredMethod("get" + name);
				Object value = get.invoke(src);

				if (value == null) {
					continue;
				}

				Method set = dest.getClass().getDeclaredMethod("set" + name, get.getReturnType());

				set.invoke(dest, value);
			} catch (Exception e) {
				// 에러 무시
			}
		}
	}
}
