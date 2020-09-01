package com.ntels.ccbs.charge.service.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.domain.common.ExrateInfo;
import com.ntels.ccbs.charge.mapper.common.PyCommMapper;
import com.ntels.ccbs.common.util.DateUtil;

@Service
public class PyCommServiceImpl implements PyCommService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
//	@Autowired
//	private CommonService commonService;

	@Autowired
	private PyCommMapper pyCommMapper;
	
	// TODO insertBondRcpt 이 작업을 위해 참조한다.
	// 애초에 공통 서비스에 다른 서비스가 온다는게 상당히 거슬리지만..
	// 위 작업을 아직 이해못했기 때문에 추 후 옮기도록 하자!
	@Autowired
	private ReceiptService receiptService;
	
//	@Autowired
//	private AssrService assrService;
	
//	@Value("${crncy.cd}")
	private String crncyCd = "KRW";
	
//	@Value("${py09m02.header.data.cnt}")
//	private int headerDataCnt;
	
//	@Value("${py09m02.data.separator}")
//	private String dataSeparator;
	
	private static final String MOD_ID_DPST = "PY001";
	
	private static final String MOD_ID_EACH_DPST = "PY002";
	
	private static final String MOD_ID_PYM = "PY003";
	
	private static final String MOD_ID_PREPAY_OCC = "PY004";
	
	private static final String MOD_ID_AMBG_OCC = "PY005";
	
	private static final String MOD_ID_PREPAY_TRANS = "PY006";
	
	private static final String MOD_ID_AMBG_TRANS = "PY007";
	
	
	@Override
	public ExrateInfo getExrateInfo() {

		// 환율 적용 날짜
		String exrateAplyDt = DateUtil.getDateStringYYYYMMDD(0);

		if (crncyCd == null) {
			crncyCd = "KRW";
		}

		return pyCommMapper.getExrateInfo(exrateAplyDt, crncyCd);
	}

	@Override
	public int getPymAcntCnt(String pymAcntId) {
		return pyCommMapper.getPymAcntCnt(pymAcntId);
	}
	
	
	
	
//	
//	
//	@Override
//	public boolean isTest() {
//		return true;
//	}
//	
//	private String getSeqNo(String modId) {
//		int seq = commonService.createNewSequence(modId);
//		return String.format("%010d", seq);
//	}
//	
//	@Override
//	public String getDpstSeqNo() {
//		return getSeqNo(MOD_ID_DPST);
//	}
//	
//	@Override
//	public String getEachDpstSeqNo() {
//		return getSeqNo(MOD_ID_EACH_DPST);		
//	}
//	
//	@Override
//	public String getPymSeqNo() {
//		return getSeqNo(MOD_ID_PYM);
//	}
//	
//	@Override
//	public String getPrepayOccSeqNo() {
//		return getSeqNo(MOD_ID_PREPAY_OCC);		
//	}
//	
//	@Override
//	public String getAmbgOccSeqNo() {
//		return getSeqNo(MOD_ID_AMBG_OCC);
//	}
//	
//	@Override
//	public String getPrepayTransSeqNo() {
//		return getSeqNo(MOD_ID_PREPAY_TRANS);
//	}
//	
//	@Override
//	public String getAmbgTransSeqNo() {
//		return getSeqNo(MOD_ID_AMBG_TRANS);
//	}
//	
//	
//	/**
//	 * Job런처가 실행될 때 Arguments로 넘어온 데이터를 파싱하여
//	 * PyCommEntity객체로 넘겨준다.
//	 * @param arg
//	 * @return
//	 */
//	@Override
//	public PyCommEntity parseArgData(String data) {
//		PyCommEntity pyCommEntity = new PyCommEntity();
//		
//		String[] split = data.split(dataSeparator);
//		
//		// TODO 아직 데이터 형식에 대한 확신이 없다.. PRO*C소스에서의 파싱부분을 추 후 다시 살펴봐야 할 필요가 있다.
//		if (StringUtils.isEmptyOrWhitespaceOnly(split[1]) == false) {
//			// record data
//			pyCommEntity.setRecordData(Integer.parseInt(split[1]));
//		}
//		
//		if (StringUtils.isEmptyOrWhitespaceOnly(split[2]) == false) {
//			// group id
//			pyCommEntity.setGroupId(split[2]);
//		}
//		
//		if (StringUtils.isEmptyOrWhitespaceOnly(split[3]) == false) {
//			// batch seq
//			pyCommEntity.setBatchSeq(Integer.parseInt(split[3]));
//		}
//		
//		if (StringUtils.isEmptyOrWhitespaceOnly(split[4]) == false) {
//			// 작업년월
//			pyCommEntity.setWorkYymm(split[4]);
//		}
//		
//		if (StringUtils.isEmptyOrWhitespaceOnly(split[5]) == false) {
//			pyCommEntity.setJobBase(split[5]);
//		}
//		
//		if (StringUtils.isEmptyOrWhitespaceOnly(split[6]) == false) {
//			pyCommEntity.setUser(split[6]);
//		}
//		
//		if (StringUtils.isEmptyOrWhitespaceOnly(split[7]) == false) {
//			pyCommEntity.setSoId(split[7]);
//		}
//		
//		String[] dataArray = new String[split.length - headerDataCnt];
//		
//		for (int i = headerDataCnt ; i < split.length; i++) {
//			dataArray[i - headerDataCnt] = split[i];
//		}
//		
//		pyCommEntity.setData(dataArray);
//		
//		return pyCommEntity;
//	}
//	
//	/**
//	 * TODO 이 메세도는 추 후 적당한 자리가 생기면 옮기도록 하자.. 리팩토링이 반드시 필요함!!
//	 */
//	@Override
//	public void insertBondRcpt(String orgId, String ifChk, String dpstSeqNo, String pymSeqNo, String regrId) {
//		// TODO TP_SVC_RATE_ITM_TYP 테이블이 제거되었으므로 이에따른 추 후 대응이 필요하다. 기존 참조 컬럼 INV_ITM_CD
//		List<ReceiptDetail> rcptDtlList = receiptService.getReceiptDetailforBondRcptTr(dpstSeqNo, pymSeqNo);
//		
//		// TODO 이 후 작업은 테이블이 삭제되어 진행하지 않음 TDNAC_BOND_RCPT_TR
//		// TODO 위 작업은 건너뛰고 계속함
//		
//		for (ReceiptDetail pyRcptDtl : rcptDtlList) {
//			AcAgncyPymInfo acAgncyPymInfo = new AcAgncyPymInfo();
//			// TODO AgncyPymInfo 시퀀스 발급
//			int seqNo = 1;
//			
//			acAgncyPymInfo.setPymDt(getDt());
//			acAgncyPymInfo.setOrgId(orgId);
//			acAgncyPymInfo.setSeqNo(seqNo);
//			acAgncyPymInfo.setPymItmCd(pyRcptDtl.getChrgItmCd());
//			acAgncyPymInfo.setPymTgtAmt(Math.round(pyRcptDtl.getBillAmt()));
//			acAgncyPymInfo.setPymAmt(Math.round(pyRcptDtl.getRcptAmt()));
//			acAgncyPymInfo.setPymMthdCd("11");
//			acAgncyPymInfo.setCtrtId(pyRcptDtl.getCtrtId());
//			acAgncyPymInfo.setAcntId(pyRcptDtl.getPymAcntId());
//			acAgncyPymInfo.setPymSeqNo(pymSeqNo);
//			acAgncyPymInfo.setSaleItmCd(pyRcptDtl.getChrgItmCd());
//			acAgncyPymInfo.setSaleAccClCd("70");
//			acAgncyPymInfo.setRegDate(now());
//			acAgncyPymInfo.setRegrId(regrId);
//			acAgncyPymInfo.setChgDate(now());
//			acAgncyPymInfo.setChgrId(regrId);
//			regrId.trim();
//			
//			pyCommDao.insertAgncyPymInfo(acAgncyPymInfo);
//
//		}
//		
//		double sumRcptAmt = receiptService.getSumRcptAmt(pymSeqNo);
//		
//		int updateCount = pyCommDao.updateLoanInfo(Math.round(sumRcptAmt), orgId);
//		
//		if (updateCount < 0) {
//			// TODO 처리해야 할 일이 있을수도... 없을수도... 혹시 모르니까 일정관리!
//		}
//	}
//	
//	/**
//	 * 납부계정ID 조회
//	 * @param pymAcntId
//	 * @return
//	 */
	
}
