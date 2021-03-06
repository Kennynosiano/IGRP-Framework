/*-------------------------*/

/*Create Controller*/

package nosi.webapps.igrp.pages.lookuplistpage;
import nosi.core.config.Config;
/*---- Import your packages here... ----*/
import nosi.core.webapp.Controller;
import nosi.core.webapp.Igrp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nosi.core.webapp.Response;
import nosi.webapps.igrp.dao.Action;
import nosi.webapps.igrp.dao.Application;

/*---- End ----*/

public class LookupListPageController extends Controller {		


	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		/*---- Insert your code here... ----*/
		LookupListPage model = new LookupListPage();
		ArrayList<LookupListPage.Table_1> lista = new ArrayList<>();
		if(Igrp.getInstance().getRequest().getMethod().toUpperCase().equals("POST")){
			model.load();		
		}
		List<Action> listActions = new Action().find()
											   .andWhere("application", "=",model.getEnv_fk()!=0? model.getEnv_fk():null)
											   .andWhere("page", "like", model.getPage())
											   .andWhere("page_descr", "like", model.getPage_descr())
											   .all();
		
		for(Action ac : listActions){
			LookupListPage.Table_1 table1 = new LookupListPage.Table_1();
			table1.setP_id(""+ac.getId());
			table1.setNome_pagina(ac.getPage());
			table1.setDescricao(ac.getPage_descr());
			table1.setP_id_aplicacao(ac.getApplication().getId());
			lista.add(table1);
		}
		LookupListPageView view = new LookupListPageView(model);
		view.p_id.setParam(true);
		view.env_fk.setLabel("Aplica��o");
		view.env_fk.setValue(new Application().getListApps());
		view.table_1.addData(lista);
		Config.target = "_blank";
		view.btn_pesquisar.setLink("index");
		return this.renderView(view);
		/*---- End ----*/
	}


	public Response actionPesquisar() throws IOException{
		/*---- Insert your code here... ----*/
		return this.redirect("igrp","ListaPage","index");
		/*---- End ----*/
	}
	
	/*---- Insert your actions here... ----*//*---- End ----*/
}
