package shine.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;

import shine.app.mapper.BoardMapper;
import shine.dao.exception.ShineException;

import com.shine.SharedConstants;
import com.shine.boards.NoticeBoard;
import com.shine.objects.ShineLocation;
import com.sp.board.BoardDao;
import com.sp.entity.NoticeBoardEntity;
import com.sp.spring.SpApplicationContext;

public class AjaxBoardLoad extends BasePhoneServlet {
	private BoardDao ndao;

	BoardMapper mapper = new BoardMapper();

	@Override
	public void init() throws ServletException {
		ndao = SpApplicationContext.getBean(BoardDao.class);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		log.debug("request received: " + req.getRequestURI() + "?" + req.getQueryString());

		ShineLocation loc = getLocation(req);
		List<NoticeBoardEntity> list = ndao.getNearBoards(loc, 0, SharedConstants.DISTANCE_TOLERANCE);

		List<NoticeBoard> boards = mapper.entityToDto(list);
		jacksoniser(boards, res.getOutputStream());

	}

	/*
	 * not implemented as doPost is overridden
	 */
	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {
		return null;
	}

	private void jacksoniser(List<NoticeBoard> boards, OutputStream os) throws IOException {

		JsonFactory f = new JsonFactory();
		JsonGenerator jgen = f.createJsonGenerator(os);
		jgen.writeStartArray();
		for (NoticeBoard noticeBoard : boards) {
			jgen.writeStartObject();
			jgen.writeStringField("name", noticeBoard.getName());
			jgen.writeStringField("creator", noticeBoard.getCreator());
			jgen.writeStringField("lat", "" + noticeBoard.getLat());
			jgen.writeStringField("lng", "" + noticeBoard.getLng());
			jgen.writeEndObject();

		}
		jgen.writeEndArray();
		jgen.flush();
		//			ObjectMapper mapper = new ObjectMapper();

	}

}
