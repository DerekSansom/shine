package shine.app;

import static com.shine.SharedConstants.AWAITING_PLAY;
import static com.shine.SharedConstants.CHALLENGER_WINS;
import static com.shine.SharedConstants.CHALLENGER_WINS_ROUND;
import static com.shine.SharedConstants.CHALLENGER_WINS_ROUND_TIMEOUT;
import static com.shine.SharedConstants.DRAW;
import static com.shine.SharedConstants.OPPONENT_WINS;
import static com.shine.SharedConstants.OPPONENT_WINS_ROUND;
import static com.shine.SharedConstants.OPPONENT_WINS_ROUND_TIMEOUT;
import static com.shine.SharedConstants.PAPER;
import static com.shine.SharedConstants.SCISSORS;
import static com.shine.SharedConstants.STONE;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.SharedConstants;
import com.shine.objects.ShineObject;

public class Challenge extends ShineObject {

	private Integer challenger, opponent, winner;
	private int bestOf = 5, challengerScore, opponentScore, toWin = 3;
	private int gameround, gameResult;
	private Date accepted;
	static final int MILLISECONDS_ALLOWED = 300000;
	private List<Round> rounds = new ArrayList<Round>();
	private Round currentRound;
	private long createdMillis, lastActivity;
	private boolean declined;

	public Challenge(Integer challenger, Integer opponent) {

		this.challenger = challenger;
		this.opponent = opponent;
		this.createdMillis = System.currentTimeMillis();
		this.lastActivity = createdMillis;
	}

	synchronized Round play(Integer p, int round, int symbol) throws ShineException {
		lastActivity = System.currentTimeMillis();
		if (symbol != PAPER && symbol != SCISSORS && symbol != STONE) {
			throw new ShineException(GeneralError.NOT_FOUND);
		}
		if (!p.equals(challenger) && !p.equals(opponent)) {
			throw new ShineException(GeneralError.NOT_FOUND);
		}

		if (currentRound == null && rounds.size() == round - 1) {
			currentRound = new Round();
			rounds.add(currentRound);
			gameround++;
		} else if (currentRound == null || rounds.size() != round) {
			//someone's got out of synch on their rounds so inform them
			throw new ShineException(GeneralError.NOT_FOUND);
		} else if (p == challenger && currentRound.challengerSymbol != 0) {
			throw new ShineException(SharedConstants.ALREADY_ACTIONED);

		} else if (p == opponent && currentRound.opponentSymbol != 0) {
			throw new ShineException(SharedConstants.ALREADY_ACTIONED);

		}
		Round thisRound = currentRound;

		setResultToRound(thisRound, p, symbol);

		if (thisRound.result == AWAITING_PLAY) {
			return thisRound;
		}

		//this round finished
		currentRound = null;
		switch (thisRound.result) {
		case CHALLENGER_WINS_ROUND:
		case CHALLENGER_WINS_ROUND_TIMEOUT:
			challengerScore++;
			break;
		case OPPONENT_WINS_ROUND:
		case OPPONENT_WINS_ROUND_TIMEOUT:
			opponentScore++;
			break;
		}
		//if this round is the clincher then set the final result to it to be picked up by contestants.
		gameResult = checkGameResult();

		if (gameResult != AWAITING_PLAY) {
			thisRound.result = gameResult;
		}

		return thisRound;
	}

	/**
	 * Returns whether there is an overall winner of the game.
	 * 
	 * @return
	 */
	private int checkGameResult() {
		if (challengerScore == toWin) {
			winner = challenger;
			return CHALLENGER_WINS;
		}
		if (opponentScore == toWin) {
			winner = opponent;
			return OPPONENT_WINS;
		}

		return AWAITING_PLAY;
	}

	/**
	 * This sets the winner of the round, either by timeout or standard play.
	 * This may later be overridden by overall winner if this is the clinching
	 * round.
	 * 
	 * @param round
	 * @param pid
	 * @param symbol
	 */
	private void setResultToRound(Round round, Integer pid, int symbol) {

		int result = 0;
		if (System.currentTimeMillis() > currentRound.start + MILLISECONDS_ALLOWED) {
			if (pid == challenger) {
				result = OPPONENT_WINS_ROUND_TIMEOUT;

			} else if (pid == opponent) {
				result = CHALLENGER_WINS_ROUND_TIMEOUT;
			}
			round.result = result;
			return;
		} else if (pid == challenger) {
			round.challengerSymbol = symbol;

		} else if (pid == opponent) {
			round.opponentSymbol = symbol;
		}

		if (round.challengerSymbol == 0 || round.opponentSymbol == 0) {
//still waiting for one
			return;
		}
		//got both symbols.
		result = calcWinner(round.challengerSymbol, round.opponentSymbol);
		round.result = result;
	}

	private int calcWinner(int challengerSymbol, int opponentSymbol) {
		if (challengerSymbol == opponentSymbol) {
			return DRAW;
		} else if (challengerSymbol == PAPER && opponentSymbol == STONE) {
			return CHALLENGER_WINS_ROUND;
		} else if (challengerSymbol == STONE && opponentSymbol == SCISSORS) {
			return CHALLENGER_WINS_ROUND;
		} else if (challengerSymbol == SCISSORS && opponentSymbol == PAPER) {
			return CHALLENGER_WINS_ROUND;
		}

		return OPPONENT_WINS_ROUND;

	}

	public Date getAccepted() {
		return accepted;
	}

	public void setAccepted(Date accepted) {
		this.accepted = accepted;
	}

	public Integer getOpponent() {
		return opponent;
	}

	class Round {
		int challengerSymbol, opponentSymbol;
		long start;
		int result = AWAITING_PLAY;

		Round() {
			start = System.currentTimeMillis();
		}

		int result() {
			return result;
		}

	}

	public int getBestOf() {
		return bestOf;
	}

	public void decline() {
		declined = true;
	}

	public boolean isDeclined() {
		return declined;
	}

	public long getCreatedMillis() {
		return createdMillis;
	}

	public Integer getChallenger() {
		return challenger;
	}

	public long getLastActivity() {
		return lastActivity;
	}

	// @Override
	public String toXml() {
		StringBuilder sb = new StringBuilder("<challenge ").
				append(formatIntAttribute("id", id)).
				append(formatIntAttribute("oppid", opponent)).
				append(formatIntAttribute("challid", challenger)).
				append(formatIntAttribute("bestof", bestOf)).
				append("/>");
		return sb.toString();

	}

	public int getChallengerScore() {
		return challengerScore;
	}

	public int getOpponentScore() {
		return opponentScore;
	}

}
