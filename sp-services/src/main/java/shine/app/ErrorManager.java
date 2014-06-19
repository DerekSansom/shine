package shine.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shine.error.ErrorReport;
import com.sp.entity.error.ErrorReportEntity;
import com.sp.error.ErrorReportDao;

@Component
public class ErrorManager {

	@Autowired
	private ErrorReportDao errorReportDao;

	public void saveReport(ErrorReport errorReport) {
		ErrorReportEntity entity = new ErrorReportEntity(errorReport.getError(),
				errorReport.getOs(),
				errorReport.getPlatform(),
				errorReport.getVersion());
		errorReportDao.saveReport(entity);

	}

}
