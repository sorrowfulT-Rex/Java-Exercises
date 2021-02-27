package tutorialquestion;// 6346: Depth of arithmetic expressions
/*
 * Description: https://github.com/afd/ProgrammingIITutorialQuestions/blob/master/questions/6346.md
 */

enum Operator {
	ADD, MUL
}

interface Expr {

	double eval();

	int numLiteral();

	int depth();
}

public class _6346 {

	public static void main(String[] args){
		var threePlusFiveTimesSix = new OpExpr(Operator.ADD, new LitExpr(3), new OpExpr(Operator.MUL, new LitExpr(5), new LitExpr(6)));

		System.out.println(threePlusFiveTimesSix.eval());
		System.out.println(threePlusFiveTimesSix.depth());
	}
}

class LitExpr implements Expr {

	private final double n;

	public LitExpr(double n) {
		this.n = n;
	}

	@Override
	public double eval() {
		return n;
	}

	@Override
	public int numLiteral() {
		return 1;
	}

	@Override
	public int depth() {
		return 0;
	}
}

class OpExpr implements Expr {
	private final Expr lhs;
	private final Expr rhs;
	private final Operator op;

	public OpExpr(Operator op, Expr lhs, Expr rhs) {
		this.op = op;
		this.lhs = lhs;
		this.rhs = rhs;
	}

	@Override
	public double eval() {
		return switch (op) {
			case ADD -> lhs.eval() + rhs.eval();
			case MUL -> lhs.eval() * rhs.eval();
		};
	}

	@Override
	public int numLiteral() {
		return lhs.numLiteral() + rhs.numLiteral();
	}

	@Override
	public int depth() {
		return 1 + Math.max(lhs.depth(), rhs.depth());
	}
}
