﻿.append("<form action=\"AddNewFilm\" method=\"post\">")
.append("<table>")
.append("<tr><td>Tytuł filmu:</td><td><input type=\"text\" name=\"title\" size=\"20px\"></td></tr>")
.append("<tr><td>Reżyser:</td><td><input type=\"text\" name=\"director\" size=\"20px\"></td></tr>")
.append("<tr><td>Data premiery:</td><td><input type=\"date\" name=\"premiere-date\" size=\"20px\"></td></tr>")
.append("<tr><td>Link do trailera:</td><td><input type=\"url\" name=\"trailer_link\" size=\"20px\"></td></tr>")
.append("<tr><td>Link do plakatu:</td><td><input type=\"url\" name=\"poster_link\" size=\"20px\"></td></tr>")
.append("<tr><td>Cena:</td><td><input type=\"text\" name=\"price\" size=\"20px\"></td></tr>")
.append("</table>")
.append("<input type=\"submit\" value=\"Dodaj nowy film\">")
.append("</form>")