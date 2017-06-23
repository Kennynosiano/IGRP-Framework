<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:output method="html" omit-xml-declaration="yes" encoding="ISO-8859-1" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>
  <xsl:template match="/">
    <html><!--XSL Generator (RED / DIM )--><!--HOME-HEADER--><xsl:call-template name="home-header"/>
      <xsl:apply-templates mode="js_validation" select="rows/content/js_validation"/>
      <body><!--HOME TOP--><xsl:call-template name="home-top-main"/><!--CENTER PANE--><div class="general">
          <div id="content">
            <div class="ui-layout-center">
              <xsl:call-template name="dialog-modal"/><!-- START YOUR CODE HERE --><div class="box-content"><!--PAGE TITLE--><xsl:call-template name="page-title">
                  <xsl:with-param name="title" select="rows/content/title"/>
                </xsl:call-template><!--END PAGE TITLE--><!-- START NOTIFICATION MESSAGES--><xsl:apply-templates mode="notif_messages" select="rows/content/messages">
                  <xsl:with-param name="class" select="'notification'"/>
                </xsl:apply-templates><!-- END NOTIFICATION MESSAGES--><form action="#" method="post" id="formular_default" name="formular_default" class="default_form" enctype="multipart/form-data"><!-- START HIDDEN FILTER--><xsl:apply-templates mode="form-hidden" select="rows/content/filter/value"/><!-- END HIDDEN FILTER--><!-- START HIDDEN FORM--><xsl:apply-templates mode="form-hidden" select="rows/content/form/value"/><!-- END HIDDEN FORM--><!-- START FORM--><!--START TOOL BAR FROM--><xsl:apply-templates mode="tools-bar" select="rows/content/form/tools-bar"/><!--END TOOL BAR FORM--><div class="box-content"><!-- INICIO SEPARADOR criar_schema--><xsl:if test="rows/content/form/label/criar_schema">
                      <xsl:if test="rows/content/form/label/criar_schema">
                        <xsl:call-template name="page-title">
                          <xsl:with-param name="title" select="rows/content/form/label/criar_schema"/>
                          <xsl:with-param name="class" select="'subtitle'"/>
                        </xsl:call-template>
                      </xsl:if>
                    </xsl:if>
                    <xsl:if test="rows/content/form/label/code">
                      <label>
                        <span>(*)<xsl:value-of select="rows/content/form/label/code"/>
                        </span>
                        <input type="text" name="{rows/content/form/value/code/@name}" value="{rows/content/form/value/code}" class="text required" maxlength="50">
                          <xsl:call-template name="FIELD_validator">
                            <xsl:with-param name="field" select="rows/content/form/label/code"/>
                          </xsl:call-template>
                        </input>
                      </label>
                    </xsl:if>
                    <xsl:if test="rows/content/form/label/description">
                      <label>
                        <span>(*)<xsl:value-of select="rows/content/form/label/description"/>
                        </span>
                        <textarea name="{rows/content/form/value/description/@name}" class="textarea required" maxlength="200">
                          <xsl:call-template name="FIELD_validator">
                            <xsl:with-param name="field" select="rows/content/form/label/description"/>
                          </xsl:call-template>
                          <xsl:value-of select="rows/content/form/value/description"/>
                        </textarea>
                      </label>
                    </xsl:if>
                  </div><!-- END FORM--></form><!-- END YOUR CODE HERE --></div>
            </div><!-- START HOME BOTTOM --><xsl:call-template name="home-side"/>
          </div>
        </div><!--HOME-FOOTER--></body>
    </html>
  </xsl:template>
  <xsl:include href="../../xsl/tmpl/IGRP-home-include.tmpl.xsl?v=20140410"/>
  <xsl:include href="../../xsl/tmpl/IGRP-JSvalidation.tmpl.xsl?v=20140410"/>
  <xsl:include href="../../xsl/tmpl/IGRP-field-validator.tmpl.xsl?v=20140410"/>
  <xsl:include href="../../xsl/tmpl/IGRP-others.tmpl.xsl?v=20140410"/>
</xsl:stylesheet>