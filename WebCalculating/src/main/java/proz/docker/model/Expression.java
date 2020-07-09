package proz.docker.model;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;

import proz.docker.service.ExpressionService;

@XmlRootElement
public class Expression {

	@NotNull
	@NotEmpty
	private String firstNum;
	
	@NotNull
	@NotEmpty
	private String op;
	
	private String secondNum;
	
	public Expression() {}
	
	public Expression(String firstNum, String secondNum, String op) {
		this.firstNum = firstNum;
		this.secondNum = secondNum;
		this.op = op;
	}
	
	public String getFirstNum() {
		return firstNum;
	}

	public void setFirstNum(String firstNum) {
		this.firstNum = firstNum;
	}

	public String getSecondNum() {
		return secondNum;
	}

	public void setSecondNum(String secondNum) {
		this.secondNum = secondNum;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}
	
	public Result calculate() {
		Result result = new Result();
		try {
			if(secondNum == null || secondNum.equals(""))
				result.setRes(ExpressionService.calculate(firstNum, op));
			else
				result.setRes(ExpressionService.calculate(firstNum, op, secondNum));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
