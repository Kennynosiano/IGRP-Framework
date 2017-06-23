<?xml version="1.0" encoding="utf-8"?><!-- DWXMLSource="xml/REDGLOBAL_TESTE_1_dash.xml" -->

        <!-- TESTE TESTE AD-HOC 1 TESTE 1 -->

        <!-- START YOUR CODE HERE -->
     <div class="box-content">

   <xsl:call-template name="page-title">
          <xsl:with-param name="title" select="rows/content/title" />
        </xsl:call-template>

        <!--NOTIFICATION MESSAGES-->
        <xsl:apply-templates mode="notif_messages" select="rows/content/messages" >
          <xsl:with-param name="class" select="'notification'"/>
        </xsl:apply-templates>


        


     </div>


        <!-- END YOUR CODE HERE -->



<xsl:call-template name="home-bottom-inc"/>
</xsl:template>
<!--TEMPLATE FOR HEAD-->
<xsl:include href="../../xsl/tmpl/IGRP-home-include.tmpl.xsl?v=2012083097"/>

<xsl:include href="../../xsl/tmpl/IGRP-field-validator.tmpl.xsl?v=2012083097"/>

</xsl:stylesheet>