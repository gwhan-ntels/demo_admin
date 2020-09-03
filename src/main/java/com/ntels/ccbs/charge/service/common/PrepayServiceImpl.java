package com.ntels.ccbs.charge.service.common;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.ntels.ccbs.charge.domain.common.PrepayOcc;
import com.ntels.ccbs.charge.mapper.common.PrepayMapper;
import com.ntels.ccbs.common.util.DateUtil;

@Service
public class PrepayServiceImpl implements PrepayService {

	@Autowired
	private PrepayMapper prepayMapper;

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
	public int updatePrepayOccCancel(String pymSeqNo) {

		if (StringUtils.isEmptyOrWhitespaceOnly(pymSeqNo) == true) {
			throw new RuntimeException("pymSeqNo값이 없어서 조회할 수 없습니다.");
		}
	
		int count = prepayMapper.getPrepayCount(pymSeqNo);
		
		// 선수금발생내역에 있으면서 선수금대체내역에 있으면 처리할 수 없음
		if (count > 0) {
			throw new RuntimeException("count is greater than 0.");
		}
		
		String cnclDttm = DateUtil.getDateStringYYYYMMDDHH24MISS(0);
		
		return prepayMapper.updatePrepayOccCancel(cnclDttm, pymSeqNo);
		
	}

	/**
	 * 선수금대체이력및 선수금발생내역를 수정한다.
	 * @param pymSeqNo
	 * @param prepayTransSeqNo
	 * @param payObjAmt
	 * @return
	 */
	@Override
	public int updatePrepayTransHistory(String pymSeqNo, String prepayTransSeqNo, double payObjAmt) {
		if (StringUtils.isEmptyOrWhitespaceOnly(pymSeqNo) == true) {
			throw new RuntimeException("pymSeqNo값이 없어서 조회할 수 없습니다.");
		}

		if (StringUtils.isEmptyOrWhitespaceOnly(prepayTransSeqNo) == true) {
			throw new RuntimeException("prepayTransSeqNo값이 없어서 조회할 수 없습니다.");
		}
		
		int check = prepayMapper.updatePrepayTransHistoryCancel(prepayTransSeqNo);
		
		if (check < 0) {
			// TODO 갱신된 항목이 없다.. 어떻게 할 것인가?? 지금은 아무것도 하지 않음
		}

		String prepayOccSeqNo = prepayMapper.getPrepayOccSeqNoFromPrepayTransHistory(prepayTransSeqNo);

		Timestamp chgDate = new Timestamp(new Date().getTime());

		check = prepayMapper.updatePrepayTransStat(payObjAmt, chgDate, prepayOccSeqNo);
		
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

}
