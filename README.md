He desarrollado un programa en Java utilizando la biblioteca de Java Swing, diseñado específicamente 
como un reproductor de partidas de ajedrez en formato PGN (Portable Game Notation). 
Este programa permite cargar y reproducir partidas de ajedrez directamente desde un archivo de texto (.txt) 
que contenga la notación PGN de una partida.

El funcionamiento es sencillo: una vez que se carga el archivo, el programa proporciona opciones para avanzar
y retroceder los movimientos de la partida de manera manual. Además, cuenta con una función de avance automático
para reproducir los movimientos de forma continua. Para mejorar la experiencia del usuario, también he incluido 
la posibilidad de alternar entre un tema claro y uno oscuro, adaptando la apariencia del reproductor a las 
preferencias del usuario.

Sin embargo, encontré un pequeño inconveniente al intentar reiniciar la partida utilizando un JOptionPane que se 
muestra al final de la reproducción(Se reinicia toda la logica del tablero pero no se muestra enseguida sino 
despues de reproducir el primer movimiento). Este error implica que, al confirmar la acción para reiniciar, 
el comportamiento esperado no se ejecuta de manera correcta, y es un aspecto que todavía necesita un ajuste.

Espero seguir optimizando el programa y solucionar este detalle en futuras versiones para 
mejorar la experiencia general del usuario.

Nota: 
Creé dos carpetas dentro de la carpeta "src", una para el javaDoc y otra para el diagrama de clases. 
Tambien creé el .jar, este se encuentra en la carpeta "disk" la cual esta al mismo nivel que "src".
