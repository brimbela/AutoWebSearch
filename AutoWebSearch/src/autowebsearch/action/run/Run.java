package autowebsearch.action.run;

import autowebsearch.action.sapo.SapoSearchAction;

public class Run {

	public static void main(String args[]){
		SapoSearchAction sapoAction = new SapoSearchAction();
		try {
			sapoAction.runTestQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
