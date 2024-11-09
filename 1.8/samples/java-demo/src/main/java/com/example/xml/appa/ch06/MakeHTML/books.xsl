<?xml version="1.0"?>
<xsl:stylesheet
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  version="1.0">
  <xsl:template match="/books">
    <html>
      <body>
        <xsl:for-each select="book">
          <br/>
          ISBN:
          <br/>
          <br/>
          Publication Year:
          <h2>
            <xsl:value-of select="normalize-space(title/text())"/>
          </h2>
          <xsl:for-each select="author">
            <br/>
            <xsl:text>
                        </xsl:text>
            <xsl:value-of select="normalize-space(text())"/>
          </xsl:for-each>
          <xsl:text>
                    </xsl:text>
          <xsl:value-of select="@isbn"/>
          <xsl:value-of select="@pubyear"/>
        </xsl:for-each>
      </body>
      <head>
        <title>Books</title>
      </head>
    </html>
  </xsl:template>
</xsl:stylesheet>
