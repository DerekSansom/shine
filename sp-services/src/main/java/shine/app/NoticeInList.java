package shine.app;

import com.shine.boards.Notice;

public class NoticeInList {

	private int previous, next;
	private Notice notice;

	public NoticeInList(Notice notice, int previous, int next) {
		super();
		this.previous = previous;
		this.next = next;
		this.notice = notice;
	}

	public int getPrevious() {
		return previous;
	}

	public void setPrevious(int previous) {
		this.previous = previous;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}
}
