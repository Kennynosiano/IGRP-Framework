var GENFORM = function(name,params){
	CONTAINER.call(this,name,params);

	var GEN = VARS.getGen();
	var container = this;

	container.sortableOptions.placeholder = 'gen-form-placeholder';

	container.contextMenu = {
		type   : 'button',
		holder : '.gen-form-btns',
		menu   : {
			selector: '>.btn',
			label   : '.btn-lbl',
		},
		xmlTag:'tools-bar'
	}

	container.includes = {
		xsl : [ 'form-utils'],
		//css : [ { path :'/core/igrp/form/igrp.forms.css' } ],
		js  : [ { path :'/core/igrp/form/igrp.forms.js'} ]
	}

	container.onLinkFieldSet = function(field){
		/*field.setPropriety({
			name:'button',
			value:false,
			xslValue:'btn btn-primary'
		});*/
		
	}

	container.onFileFieldSet = function(field){
		GEN.setBTNClass(field);
	}

	container.onDateFieldSet = function(field){
		GEN.setBTNClass(field);
	}

	container.onHiddenFieldSet = function(field){
		field.xml.tag = 'hidden';
	}

	container.onFieldSet = function(field){
		GEN.setFormFieldAttr(field);
	}

	container.ready = function(){
		//console.log('dsaljdhlsakdbhaslkd')
	}
	
	


}

this[VARS.name].declareContainer({
	name:'form',
	container:GENFORM
});