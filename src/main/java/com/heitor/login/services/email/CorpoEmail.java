package com.heitor.login.services.email;

public class CorpoEmail {

    public static String corpoEmail(String mioloEmail, String assunto) {


        return  "<!DOCTYPE html>\n"
                + "<html lang=\"pt\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n"
                + "<head>\n"
                + "    <meta charset=\"utf-8\"> <!-- utf-8 works for most cases -->\n"
                + "    <meta name=\"viewport\" content=\"width=device-width\"> <!-- Forcing initial-scale shouldn't be necessary -->\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> <!-- Use the latest (edge) version of IE rendering engine -->\n"
                + "    <meta name=\"x-apple-disable-message-reformatting\">  <!-- Disable auto-scale in iOS 10 Mail entirely -->\n"
                + "    <title></title> <!-- The title tag shows in email notifications, like Android 4.4. -->\n"
                + "    <link href=\"https://fonts.googleapis.com/css?family=Playfair+Display:400,400i,700,700i\" rel=\"stylesheet\">\n"
                + "<style>\n"
                + "table {\r\n"
                + "  		border-collapse: collapse;\r\n"
                + "  		width: 100%;\r\n"
                + "}\r\n"
                + "th, td {\r\n"
                + "  	text-align: left;\r\n"
                + "		padding: 8px;\r\n"
                + "}\r\n"
                + "tr:nth-child(even) {background-color: #f2f2f2;}"
                + "\n"
                + ".primary{\n"
                + "		background: #f3a333;\n"
                + "}\n"
                + "\n"
                + ".bg_white{\n"
                + "		background: #ffffff;\n"
                + "}\n"
                + ".bg_light{\n"
                + "		background: #fafafa;\n"
                + "}\n"
                + ".bg_seprara{\n"
                + "		position: absolute;\r\n"
                + "    	left: 0;\r\n"
                + "    	right: 0;\r\n"
                + "    	bottom: -10px;\r\n"
                + "    	content: '';\r\n"
                + "    	width: 100%;\r\n"
                + "    	height: 1px;\r\n"
                + "    	background: #00008B;\r\n"
                + "    	margin: 0 auto;"
                + "}\n"
                + ".bg_black{\n"
                + "		background: #00008B;\n"
                + "}\n"
                + ".bg_dark{\n"
                + "		background: #FFFFFF;\n"
                + "}\n"
                + ".email-section{\n"
                + "		padding:2.5em;\n"
                + "}\n"
                + "h1,h2,h3,h4,h5,h6{\n"
                + "		font-family: 'Playfair Display', serif;\n"
                + "		color: #000000;\n"
                + "		margin-top: 0;\n"
                + "}\n"
                + "u{\n"
                + "		text-decoration: underline overline wavy #f3a333;\n"
                + "}\n"
                + "\n"
                + "body{\n"
                + "		font-family: 'Montserrat', sans-serif;\n"
                + "		font-weight: 400;\n"
                + "		font-size: 15px;\n"
                + "		line-height: 1.8;\n"
                + "		color: rgba(0,0,0,.4);\n"
                + "}\n"
                + "\n"
                + "a{\n"
                + "		color: #FFFFFF;\n"
                + "}\n"
                + "\n"
                + ".heading-section .subheading {\n"
                + "    margin-bottom: 20px !important; \n"
                + "    display: inline-block; \n"
                + "    font-size: 13px;\n"
                + "     text-transform: uppercase;\n"
                + "     letter-spacing: 2px;\n"
                + "     color: rgba(0,0,0,.4);\n"
                + "     position: relative;\n"
                + "     font-family: Fira Sans,sans-serif;\n"
                + " }\n"
                + ".heading-section .subheading::after{\n"
                + "		position: absolute;\n"
                + "		left: 0;\n"
                + "		right: 0;\n"
                + "		bottom: -10px;\n"
                + "		content: '';\n"
                + "		width: 100%;\n"
                + "		height: 2px;\n"
                + "		background: #f3a333;\n"
                + "		margin: 0 auto;\n"
                + "}\n"
                + "\n"
                + ".footer{\n"
                + "		color: rgba(255,255,255,.5);\n"
                + "}\n"
                + ".footer .heading{\n"
                + "		color: #ffffff;\n"
                + "		font-size: 20px;\n"
                + "}\n"
                + "\n"
                + "</style>\n"
                + "\n"
                + "</head>\n"
                + "\n"
                + "<body width=\"100%\" style=\"margin: 0; padding: 0 !important; mso-line-height-rule: exactly; background-color: #222222;\">\n"
                + "	<center style=\"width: 100%; background-color: #f1f1f1;\">\n"
                + "    <div style=\"display: none; font-size: 1px;max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden; mso-hide: all; font-family: sans-serif;\">\n"
                + "      &zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;\n"
                + "    </div>\n"
                + "    <div style=\"max-width: 800px; margin: 0 auto;\" class=\"email-container\">\n"
                + "    	<!-- BEGIN BODY -->\n"
                + "      <table align=\"center\" role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"margin: auto;\">\n"
                + "      	<tr>\n"
                + "          <td class=\"bg_dark email-section\" style=\"text-align:center;\">\n"
                + "			<img class=\"hero\" src=\"https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png\" alt=\"Smiley face\" align=\"middle\">\n"
                + "		  <!-- <h1><a href=\"#\">Meta Brasil</a></h1> -->\n"
                + "          </td>\n"
                + "	      </tr><!-- end tr -->\n"
                + "		  <tr>\n"
                + "		      <td class=\"bg_seprara\"></td>\n"
                + "		  </tr>\n"
                + "			\n"
                + "	      <tr>\n"
                + "		      <td class=\"bg_white\">\n"
                + "		        <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n"
                + "		    \n"
                + "		          <tr>\n"
                + "		            <td class=\"bg_white email-section\">\n"
                + "		            	<div class=\"heading-section\" style=\"text-align: center; padding: 0 30px;\">\n"
                + "		            		<span class=\"subheading\"><u>"+assunto.toUpperCase()+"</u></span>\n"
                + "		              	</div>\n"
                + 			mioloEmail
                + "		            </td>\n"
                + "		          </tr><!-- end: tr -->\n"
                + "\n"
                + "				     \n"
                + "		        </table>\n"
                + "\n"
                + "		      </td>\n"
                + "		    </tr><!-- end:tr -->\n"
                + "      <!-- 1 Column Text + Button : END -->\n"
                + "     <!-- "
                // + "		</table>\n"
                // + "      <table align=\"center\" role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"margin: auto;\">\n"
                + "      	-->"
                + "			<tr>\n"
                + "          <td valign=\"middle\" class=\"bg_black footer email-section\">\n"
                + "            <table>\n"
                + "            	<tr>\n"
                + "                <td valign=\"top\" width=\"50%\" style=\"padding-top: 20px;\">\n"
                + "                  <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n"
                + "                    <tr>\n"
                + "                      <td style=\"text-align: left; padding-right: 10px;\">\n"
                + "                      	<h3 class=\"heading\">SOBRE</h3>\n"
                + "                      	<p>Nossa missão é organizar as informações do mundo para que sejam universalmente acessíveis e úteis para todos.</p>\n"
                + "                      </td>\n"
                + "                    </tr>\n"
                + "                  </table>\n"
                + "                </td>\n"
                + "                <td valign=\"top\" width=\"50%\" style=\"padding-top: 20px;\">\n"
                + "                  <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n"
                + "                    <tr>\n"
                + "                      <td style=\"text-align: left; padding-left: 5px; padding-right: 5px;\">\n"
                + "                      	<h3 class=\"heading\">LOCALIZAÇÃO</h3>\n"
                + "							<p>oulevard Corporate Tower Av. dos Andradas, 3000 - Andares 14-17 Santa Efigênia Belo Horizonte CEP: 30260-070, Brasil Telefone: +55-31-2128-6800</p>\n"
                + "                      </td>\n"
                + "                    </tr>\n"
                + "                  </table>\n"
                + "                </td>\n"
                + "                <td valign=\"top\" width=\"50%\" style=\"padding-top: 20px;\">\n"
                + "                 \n"
                + "                </td>\n"
                + "              </tr>\n"
                + "            </table>\n"
                + "          </td>\n"
                + "        </tr><!-- end: tr -->\n"
                + "        <tr>\n"
                + "        	<td valign=\"middle\" class=\"bg_black footer email-section\">\n"
                + "        		<table>\n"
                + "            	<tr>\n"
                + "                <td valign=\"top\" width=\"33.333%\">\n"
                + "                  <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n"
                + "                    <tr>\n"
                + "                      <td style=\"text-align: left; padding-right: 10px;\">\n"
                + "                      	<p>&copy; 2019 Meta All rights reserved</p>\n"
                + "                      </td>\n"
                + "                    </tr>\n"
                + "                  </table>\n"
                + "                </td>\n"
                + "                <td valign=\"top\" width=\"33.333%\">\n"
                + "                  <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n"
                + "                    <tr>\n"
                + "                      <td style=\"text-align: right; padding-left: 5px; padding-right: 5px;\">\n"
                + "                      	\n"
                + "                      </td>\n"
                + "                    </tr>\n"
                + "                  </table>\n"
                + "                </td>\n"
                + "              </tr>\n"
                + "            </table>\n"
                + "        	</td>\n"
                + "        </tr>\n"
                + "      </table>\n"
                + "\n"
                + "    </div>\n"
                + "  </center>\n"
                + "</body>\n"
                + "</html>";

    }
}
