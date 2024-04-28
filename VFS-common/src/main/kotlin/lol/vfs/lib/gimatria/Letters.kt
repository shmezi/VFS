package lol.vfs.lib.gimatria

enum class Letters(val value: Int, val letter: Char) {
   Alef(1, 'א'),
   Bet(2, 'ב'),
   Gimel(3, 'ג'),
   Dalet(4, 'ד'),
   He(5, 'ה'),
   Vav(6, 'ו'),
   Zayin(7, 'ז'),
   Chet(8, 'ח'),
   Tet(9, 'ט'),
   Yod(10, 'י'),
   Kaf(20, 'כ'),
   Lamed(30, 'ל'),
   Mem(40, 'מ'),
   Nun(50, 'נ'),
   Samech(60, 'ס'),
   Ayin(70, 'ע'),
   Pe(80, 'פ'),
   Tsadi(90, 'צ'),
   Qof(100, 'ק'),
   Resh(200, 'ר'),
   Shin(300, 'ש'),
   Tav(400, 'ת');

   companion object {
      fun bigToSmall() = entries.reversed()
   }
}
