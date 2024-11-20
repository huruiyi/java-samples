<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  version="1.0">
  <xsl:template match="/recipe">
    <html>
      <body>
        <h2>
          <xsl:value-of select="normalize-space(title)"/>
        </h2>

        <h3>Ingredients</h3>

        <h3>Instructions</h3>

        <ul>
          <xsl:for-each select="ingredients/ingredient">
            <li>
              <xsl:if test="@qty">(<xsl:value-of
                select="@qty"/>)
              </xsl:if>
              <xsl:value-of select="normalize-space(text())"/>
            </li>
          </xsl:for-each>
        </ul>

        <xsl:value-of select="normalize-space(instructions)"/>
      </body>

      <head>
        <title>Recipes</title>
      </head>
    </html>
  </xsl:template>
</xsl:stylesheet>
