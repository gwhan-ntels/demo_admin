package com.ntels.ccbs.common.consts;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.ntels.nisf.util.PropertiesUtil;

/**
 * 
 * @author kgw
 *
 */
public abstract class Consts {
	
	public abstract static class COMMON_GROUP_CODE {
		public static final String COUNTRY_GROUP_CODE 	= "000100";		// 국가그룹코드
		public static final String LANGUAGE_GROUP_CODE 	= "000200";		// 언어그룹코드
	}
	
	public abstract static class SEQ_CODE {
		//Interface
		public static final String SEQ_IF_MSG_ID     	    = "IF001";		// Interface Message ID
		
		
		//Common
		public static final String SEQ_TSYCO_WORK_HIST 	    = "SEQ01";		// TSYCO_WORK_HIST.SEQ
		public static final String SEQ_TSYCO_WORK_HIST2 	= "SEQ02";		// TSYCO_WORK_HIST.SEQ
		public static final String SEQ_TSYCO_WORK_HIST3 	= "SEQ03";		// TSYCO_WORK_HIST.SEQ
		public static final String SEQ_TSYCO_WORK_HIST4 	= "SEQ04";		// TSYCO_WORK_HIST.SEQ
		public static final String SEQ_TSYCO_NOTICE			= "SEQ05";		// 공지사항 ID TSYCO_NOTICE(NOTICE_ID )
		public static final String SEQ_TSYCO_NOTICE_FILE	= "SEQ06";		// 공지사항 첨부파일 ID(FILE_ID )
		public static final String SEQ_TSYCO_NOTICE_AUTH 	= "SEQ07";		// 공지사항 권한 ID(AUTH_ID)
		public static final String SEQ_TSYIF_REQUEST_LOG 	= "SEQ08";		// 인터페이스 로그 시퀀스
		public static final String SEQ_TSYCO_APRVMNG_MAST 	= "SEQ09";		// TSYCO_APRVMNG_MAST
		public static final String SEQ_TSYCO_APRV_MAST 		= "SEQ10";			// TSYCO_APRV_MAST
		
		//Product
		public static final String PD_TPMBI_ATTR 	= "PD000";		// 상품속성 ID(ATTR_CD)
		public static final String PD_TPMSV_SVC 	= "PD001";		// 상품서비스 ID(SVC_CD)
		public static final String PD_TPMPD_SVC_RATE_ITM_TYP 	= "PD002";		// 상품서비스과금항목유형 ID(SVC_RATE_ITM_TYP_CD)		
		public static final String PD_TPMBI_SALE_ITM  	= "PD003";		// 상품매출 항목 ID(SALE_ITM_CD)
		public static final String PD_TPMPD_PROD  	= "PD004";		// 상품개발(운영)ID(PROD_CD)
		public static final String PD_TPMPD_D_PROD  = "PD005";		//상품코드 ID(PROD_CD)
		public static final String PD_TPMBI_RATE_ITM_TYP = "PD006";	//요금유형 코드 생성(RATE_ITM_TYP_CD)
		public static final String PD_TPMBI_INV_ITM = "PD007";	//청구항목 코드 생성(INV_ITM_CD)
		public static final String PD_TPMBI_PROD_CONF_HIST = "PD008";	//승인요청코드 생성(PROC_CD)
		public static final String PD_TPMBI_FCTR = "PD009";	//요소코드 생성(FCTR_CD)
		public static final String PD_TPMPD_RATE_ITM = "PD010";	//과금항목코드(RATE_ITM_CD)
		public static final String PD_TPMPD_ALWNCE = "PD011";	//공제코드(ALWNCE_CD)
		public static final String PD_RATE_ITM_CD = "PD012";	//과금항목 추출용 코드(RATE_ITM_CD)
		public static final String PD_TPMPD_RATE_ITM_COND = "PD013";	//조건번호(COND_NO)
		public static final String PD_TPMPD_PROD_CONF_DTL = "PD014";	//(CONF_REQ_CD)
		public static final String PD_TPMPD_WRK = "PD015";   //작업정의관리 ID(WRK_DEF_ID)
		
		
		
		//CM
		public static final String CM_PYM_ACNT_ID 	= "CM001";		// 납부계정ID
		public static final String CM_CUST_ID   	= "CM002";		// 고객ID
		public static final String CM_RCPT_ID   	= "CM003";		// 상담ID
		public static final String CM_CTRT_ID   	= "CM004";		// 계약ID
		public static final String CM_PROD_CMPS_ID  = "CM005";		// 상품구성ID
		public static final String CM_SVC_CMPS_ID   = "CM006";		// 서비스구성ID
		public static final String CM_ORDER_ID	    = "CM007";		// 오더ID
		public static final String CM_CTRT_EXT_ID	    = "CM008";  // 확장ID
		public static final String CM_SERVICE_ID    = "CM009";		// 서비스ID
		public static final String CM_WRK_GRP_ID    = "CM010";		// 작업ID
		public static final String CM_WRK_SEQ_ID    = "CM011";		// 작업일련번호
		public static final String CM_WRK_HIST_SEQ_ID    = "CM012"; // 작업이력일련번호
		public static final String CM_CUST_EXT_ID   ="CM013";       //고객정보 확장ID
		public static final String CM_PROM_CTRT_ID  ="CM014";       //약정ID
		public static final String CM_THRWY_CHRG_ID  ="CM015";       //일회성요금ID
		//SCM		
		public static final String DT_EQT_CL_ID		= "DT000";		// TDNDT_ITEM의 EQT_CL_ID ID
		public static final String DT_PRC_DTL_SEQ	= "DT001";		// TDNDT_MNCO_UT_PRC_DTL의 PRC_DTL_SEQ
		public static final String DT_PRC_DTL_SEQ2	= "DT002";		// TDNDT_DSTRB_UT_PRC_DTL의 PRC_DTL_SEQ
		public static final String DT_PO_NO			= "DT003";		// TDNDT_PO의 PO_NO
		public static final String DT_ACTNC_NO		= "DT004";		// TDNDT_ACTNC의 ACTNC_NO
		public static final String DT_INOUT_ID		= "DT005";		// TDNDT_INOUT의 INOUT_ID
		public static final String DT_USIM_SEQ		= "DT006";		// TDNDT_USIM의 USIM_SEQ
		public static final String DT_HIST_SEQ		= "DT007";		// TDNDT_UPD_PROC_HIST의 HIST_SEQ
		public static final String DT_ASGN_NO		= "DT008";		// TDNDT_ASGN의 ASGN_NO
		public static final String DT_ORD_NO		= "DT009";		// TDNDT_ORD의 ORD_NO
		public static final String DT_LOAN_USE_ORD	= "DT010";		// TDNDI_LOAN_USE_HIST의 LOAN_USE_ORD
		public static final String DT_CRT_SEQ_NO	= "DT011";		// TDNDT_VEQT_TRANS의 CRT_SEQ_NO
		
		public static final String AC_ACC_NO		= "AC001";		// TDNAC_BOND_RCPT_TR의 ACC_NO		
		
		//CH & BL
		public static final String BL_PGM_EXE_SEQ_NO = "BL001";     // TBLIV_BAT_PGM_LOG의 PGM_EXE_SEQ_NO		
		public static final String BAT_GRP_SEQ_ID	 = "BAT01";		// TBLIV_BAT_GRP의 BAT_GRP_ID
		public static final String CH_CLC_WRK_NO	 = "CH001";		// TBLCH_CLC_MAIN의 CLC_WRK_NO
		
		//청구&수납
		public static final String MOD_ID_DPST = "PY001";
		public static final String MOD_ID_EACH_DPST = "PY002";
		public static final String MOD_ID_PYM = "PY003";
		public static final String MOD_ID_PREPAY_OCC = "PY004";
		public static final String MOD_ID_AMBG_OCC = "PY005";
		public static final String MOD_ID_PREPAY_TRANS = "PY006";
		public static final String MOD_ID_AMBG_TRANS = "PY007";
		
		
		
//        public static final String PY_DPST_NO = "PY001"; //입금일련번호
//        public static final String PY_EACH_NO = "PY002"; //개별입금번호-TBLPY_EACH_DPST의 EACH_DPST_SEQ
//        public static final String PY_RCPT_NO = "PY003"; //수납일련번호
//        public static final String PY_PRPY_NO = "PY004"; //선수금발생일련번호
//        public static final String PY_PRTR_NO = "PY005"; //선수금대체일련번호
//        public static final String PY_TRNS_NO = "PY006"; //대체신청번호-TBLPY_TRANS_APPL의  TRANS_APPL_NO
//        
//        public static final String BL_GWTD_NO = "BLN04"; //지로입금일련번호-TBLPY_GIRO_WTDRAW_REQ_RSLT의 WTDRAW_REQ_NO
//        public static final String BL_EWTD_NO = "BLN05"; //EDI입금일련번호
//        public static final String BL_CWTD_NO = "BLN06"; //카드입금일련번호-TBLPY_CARD_WTDRAW_REQ_RSLT, TBLPY_CARD_TM_WTDRAW_REQ_RSLT, TBLPY_CARD_APP_WTDRAW_REQ_RSLT의 WTDRAW_REQ_NO
//        public static final String BL_VWTD_NO = "BLN07"; //가상계좌입금일련번호
//        public static final String BL_AMBG_NO = "BLN08"; //불명금발생일련번호
//        public static final String BL_ASSR_NO = "BLN09"; //보증금발생일련번호
//        public static final String BL_AMTR_NO = "BLN11"; //불명금대체일련번호
//        public static final String BL_ASTR_NO = "BLN12"; //보증금대체일련번호
//
//        public static final String BL_FCRT_NO = "BLN15"; //파일 생성 일련번호-TBLIV_BILL_FILE_CRT의 FILE_CRT_SEQ_NO
//        public static final String BL_WTRQ_NO = "BLN16"; //자동이체(EDI)신청 데이터 일련번호
//        public static final String BL_CDBT_NO = "BLN17"; //카드 배치 요청 번호-TBLPY_CARD_BAT_WTDRAW_REQ_RSLT의 CARD_BAT_REQ_NO
//        public static final String BL_CASH_NO = "BLN18"; //현금영수증발행번호
//        public static final String BL_RFBT_NO = "BLN19"; //수납파일일괄처리번호
//        public static final String BL_RERP_NO = "BLN20"; //수납ERP전송일련번호-12자리
		
		
		//Statistics 
		public static final String ST_STMT_CD	= "ST001";		// TSYCO_DEFN_STMT의 STMT_CD	
	}
	
	//상품속성
	public abstract static class PROD_ATTR {
		public static final String IS_PENALTY 	= "AT024";          //약정여부
		public static final String PENALTY_12 	= "AT026";          //12개월
		public static final String PENALTY_24 	= "AT027";          //24개월
		public static final String PENALTY_36 	= "AT028";          //36개월
		
	}
	
	

	public abstract static class SEQ_DATE_FORMAT {
		public static final String FORMAT_YYYYMMDD 	= "YYYYMMDD";		
		public static final String FORMAT_YYMMDD 	= "YYMMDD";		
		public static final String FORMAT_YYYYMM 	= "YYYYMM";		
		public static final String FORMAT_YYMM 		= "YYMM";		
		public static final String FORMAT_YYYY 		= "YYYY";		
		public static final String FORMAT_YY 		= "YY";		
	}
	
	//서비스관리
	public abstract static class SVC_MGT_CODE {
		public static final String MAIN_SVC_UPR_SVC_CD = "0";		//상위서비스코드 설정
		public static final String MAIN_SVC_UPR_SO_ID = "00";		//사업구분 상위서비스코드 설정
		public static final String MAIN_SVC_FL_YES = "M"; //주서비스
		public static final String MAIN_SVC_FL_NO = "V"; //종속서비스
		public static final String MAX_DATE = "99991231"; //MAX DATE
		public static final String MSTR_FL_YES = "1";	//원부여부
		public static final String MSTR_FL_NO = "0";	//원부여부	
	    public static final String EXPIRE_YES = "2"; //종료
	    public static final String EXPIRE_NO = "1"; //변경
	
	    /** 중복 테이블 메세지 처리 관련. */
	    public static final String TPMPD_SVC_RATE_ITM_TYP = "[서비스과금항목유형(TPMPD_SVC_RATE_ITM_TYP)] ";

	    /* The T p_ sv c_ poin t_ sav e_ dtl. */
	    public static final String TP_SVC_POINT_SAVE_DTL = "[서비스포인트산출상세(TP_SVC_POINT_SAVE_DTL)] ";

	    /* The T p_ sv c_ cmp s_ itm. */
	    public static final String TP_SVC_CMPS_ITM = "[서비스구성항목(TP_SVC_CMPS_ITM)] ";

	    /* The T pmsv_ svc. */
	    public static final String TPMSV_SVC = "[서비스(TPMSV_SVC)] ";

	    /* The T pmpd_ supr t_ equip. */
	    public static final String TPMPD_SUPRT_EQUIP = "[상품제공장비(TPMPD_SUPRT_EQUIP)] ";

	    /* The T pmbi_ sal e_ itm. */
	    public static final String TPMBI_SALE_ITM = "[매출항목(TPMBI_SALE_ITM)] ";

	    /* The T pmbi_ rat e_ itm. */
	    public static final String TPMBI_RATE_ITM = "[과금항목(TPMBI_RATE_ITM)] ";

	    /* The T pmpd_ pro d_ svc. */
	    public static final String TPMPD_PROD_SVC = "[상품서비스(TPMPD_PROD_SVC)] ";

	    /* The T pmpd_ d_ supr t_ equip. */
	    public static final String TPMPD_D_SUPRT_EQUIP = "[상품제공장비(개발)(TPMPD_D_SUPRT_EQUIP)] ";

	    /* The T pmpd_ d_ rat e_ itm. */
	    public static final String TPMPD_D_RATE_ITM = "[과금항목(개발)(TPMPD_D_RATE_ITM)] ";

	    /* The T pmpd_ d_ pro d_ svc. */
	    public static final String TPMPD_D_PROD_SVC = "[상품서비스(개발)(TPMPD_D_PROD_SVC)] ";

	    /* The T p_ band. */
	    public static final String TP_BAND = "[대역(TP_BAND)] ";	    
		
	    /* The T p_ dis c_ excl. */
	    public static final String TPMPD_DISC_EXCL = "[할인배타(TP_DISC_EXCL)]";
	    
	    /* The T p_ dis c_ sv c_ rat e_ it m_ typ. */
	    public static final String TPMPD_DISC_SVC_RATE_ITM_TYP = "[할인대상서비스과금항목유형(TP_DISC_SVC_RATE_ITM_TYP)]";
	    
	    /* The T p_ d_ dis c_ sv c_ rat e_ it m_ typ. */
	    public static final String TPMPD_D_DISC_SVC_RT_ITM_TYP = "[할인대상서비스과금항목유형(개발)(TP_D_DISC_SVC_RATE_ITM_TYP)]";

	    /* The T p_ d_ dis c_ excl. */
	    public static final String TPMPD_D_DISC_EXCL = "[할인배타(개발)(TP_D_DISC_EXCL)]";

	    /* The T p_ sv c_ rat e_ it m_ ty p_ attr. */
	    public static final String TPMPD_SVC_RATE_ITM_TYP_ATTR = "[서비스과금항목유형속성(TP_SVC_RATE_ITM_TYP_ATTR)]";

	    /* The T p_ sv c_ rat e_ it m_ ty p_ fctr. */
	    public static final String TPMPD_SVC_RATE_ITM_TYP_FCTR = "[서비스과금항목유형요소(TP_SVC_RATE_ITM_TYP_FCTR)]";	    
		
	    /* The T p_ rat e_ itm. */
	    public static final String TPMPD_RATE_ITM = "[과금항목(TP_RATE_ITM)] ";
	    
	}
	
	//상품관리
	public abstract static class PROD_MGT_CODE {
		public static final String CONF_CANCEL = "4";
		
		/** 요금영역 관련. */
	    /** 요금영역 : 일회성 */
	    public static final String CHRG_CTGRY_N = "N";

	    /** 요금영역 : 정액. */
	    public static final String CHRG_CTGRY_R = "R";

	    /** 요금영역 : 종량. */
	    public static final String CHRG_CTGRY_U = "U";

	    /** 요금영역 : 공제. */
	    public static final String CHRG_CTGRY_A = "A";

	    /** 요금영역 : 감면. */
	    public static final String CHRG_CTGRY_M = "M";

	    /** 요금영역 : 할인. */
	    public static final String CHRG_CTGRY_D = "D";

	    /** 속성유형 : 일회성. */
	    public static final String ATTR_TYP_31 = "31";

	    /** 속성유형 : 정액. */
	    public static final String ATTR_TYP_32 = "32";

	    /** 속성유형 : 종량. */
	    public static final String ATTR_TYP_33 = "33";

	    /** 속성유형 : 공제. */
	    public static final String ATTR_TYP_41 = "41";

	    /** 속성유형 : 감면. */
	    public static final String ATTR_TYP_42 = "42";

	    /** 속성유형 : 할인. */
	    public static final String ATTR_TYP_43 = "43";

	    /** 속성유형 : 기타. */
	    public static final String ATTR_TYP_91 = "91";
	    
		/** 속성유형 : 프로모션. */
	    public static final String ATTR_TYP_PROMOTION = "22";
		
	    /** 상품유형 : 프로모션. */
	    public static final String PROD_TYP_PROMOTION = "80";	

	    /** 속성유형 : 일반상품. */
	    public static final String ATTR_TYP_PRODUCT = "21";
	    
	    /** 상품관계 : 배타. */
	    public static final String PROD_REL_TYP_EXCLUSION = "X";
	    /** 상품유형 : 부가. */
	    public static final String PROD_TYP_LIKE_ADDITION = "2";
	    /** 상품관계 : 종속. */
	    public static final String PROD_REL_TYP_DEPENDENCY = "P";	    
	    /** 상품유형 : 기본. */
	    public static final String PROD_TYP_LIKE_BASIC = "1";	
	    /** 상품관계 : 전환. */
	    public static final String PROD_REL_TYP_CONVERSION = "C";
	    
	    /** 요율참조 유형 : Default. */
	    public static final String RATE_REF_TYP = "2";		    
	    
	    /** 승인 *. */
	    public static final String CONF_RSLT_CONF = "1";		
	    /** 반려 *. */
	    public static final String CONF_RSLT_RETURN = "2";
		
	    //개발상태
	    /** 개발중. */
	    public static final String DEV_ING = "1";

	    /** 승인요청중. */
	    public static final String SUBMIT_ING = "2";

	    /** 승인완료. */
	    public static final String CONFIRMOK = "3";

	    /** 반려. */
	    public static final String CONFIRMRETURN = "4";	
		
	    /** 이관완료. */
	    public static final String TRANSFER = "5";

	    /** 개발취소. */
	    public static final String DEV_CANCEL = "6";
		
	    //승인상태
	    /** 승인요청 진행중. */
	    public static final String CONF_REQ_ING = "1";

	    /** 승인요청. */
	    public static final String CONF_REQ = "2";

	    /** 승인완료. */
	    public static final String CONF_RETURN_OK = "3"; 
	    
	    //개발상태관련 공통 코드
	    /** 신규. */
	    public static final String PROD_INSERT = "1";

	    /** 변경추출. */
	    public static final String PROD_UPT = "2";

	    /** 복사. */
	    public static final String PROD_CPY = "1";	    
	    
	    //상품변경종류
	    /** 변경 *. */
	    public static final String PROD_MOD_TYP_UPDATE = "1";

	    /** 종료 *. */
	    public static final String PROD_MOD_TYP_EXPIRE = "2";	   
	    
	}
	
	
	/**
	 * 
	 * @author kgw
	 *
	 */
	public abstract static class LanguageCode {
		public static final String ENGLISH 		= "en";		// 영어
		public static final String KOREAN 		= "ko";		// 한국어
	}

	/**
	 * 언어 코드 맵 : 서비스 국가 확장시에 확장되는 언어코드를 추가해줘야한다.
	 */
	public static final Map<String, String> LANGUAGE_CODE_MAP;
	static {
		Map<String, String> map = new HashMap<String, String>();
		map.put(LanguageCode.ENGLISH, "US");
		map.put(LanguageCode.KOREAN, "KR");
		LANGUAGE_CODE_MAP = Collections.unmodifiableMap(map);
	}
	
	/**
	 * 다국어 메뉴명 사용 여부
	 */
	public static final String MULTILINGUAL_MENU_NAME = PropertiesUtil.get("config", "multilingual.menuname");
	
	public static final String ENCODE_KEY = "ntels!@tkdlxmwnth!2dlqksjd123456";
	
	
	public static enum ExcelFormatType {
		STRING, NUMBER_WITH_COMMA_POINT, NUMBER_WITH_COMMA,NUMBER, DATE
	}
	
	public static final String IF_ADM_ID = "admin";

	
    /**
     * 결재 처리 상태 : BL00018
     */
    public abstract static class DCSN_PROC_STAT {
        /** 신청 = 01 */
        public static final String APPLIED = "01";
        /** 승인(완료) = 04 */
        public static final String APPROVED = "04";
    }
    /**
     * 대체신청구분
     */
    public abstract static class TRANS_APPL_CL {
        /** 선수금 = 01 */
        public static final String PRE_PAY = "01";
        /** 불명금 = 02 */
        public static final String AMBG = "02";
        /** 보증금 = 03 */
        public static final String ASSURANCE = "03";
    }

    /**
     * 대체타입
     */
    public abstract static class AMBG_REPL_TP {
        /** 선수금 수납대체 : BL00017 */
        public static final String PRE_PAY_REP_RECEIPT = "02";
        public static final String PRE_PAY_PYM_ACNT = "04"; //jes_20180309
        /** 보증금 수납대체 : BL00019 */
        public static final String ASSURANCE_REP_RECEIPT = "01";
        /** 불명금 수납대체 : BL00020 */
        public static final String AMBG_REP_RECEIPT = "01";
        /** 선수금 보증금 대체 */
        public static final String ASSR_PREPAY_RECEIPT = "03";
    }

}

