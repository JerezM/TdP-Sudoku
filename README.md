# Sudoku

## Consideraciones
* El tablero y el timer recién se podrán visualizar  una vez se toque nuevo juego y se elija un archivo de .txt valido.

* Restricciones para la validación del archivo:
    * El contenido debe iniciar en la primera linea y primer lugar del archivo.
    * De contener espacios ya sea al final de una linea, o saltos de linea al final innecesarios,
      el archivo se tomara como invalido.
    * Solo aceptara archivos del tipo .txt
    * De ingresar un archivo invalido, entonces el juego finalizara.

* Consideraciones del timer:
    * El timer se inicializa una vez se ingresa un archivo valido, lo que da como efecto la visualización del tablero, 
      y así el inicio del timer.
    * Si el botón verificar tablero se presiona y el juego se encuentra en un estado ganador,
      entonces se detendrá el timer y se mostrara el tiempo que tardo en resolver el sudoku por pantalla.

* Consideraciones verificar tablero:
    * Al presionar verificar tablero, de haber errores en el tablero, las celdas en conflicto se visualizaran en rojo
      marcando así un error en las mismas. 
    * Tener en cuenta que también se van a marcar en rojo las celdas repetidas, incluyendo también a las celdas 
      que vienen fijas con el tablero para así poder hacerle entender al cual es el conflicto.
    * Los errores se detectan cuando, hay celdas vaciás o cuando se repite un mismo valor en una fila, columna o panel.
    * Si al presionar verificar tablero el juego se encuentra en un estado ganador,
      entonces el juego se dará por finalizado y luego de mostrar el tiempo que tardo, se cerrara el juego.

* Inserción de celdas:
  * Para insertar un valor en una celda simplemente se deberá clickear la celda objetivo en la cual nos gustaría insertar algo,
    y luego seleccionar una opción del panel de opciones que se encuentra abajo del tablero.

* Puede llegar a pasar que haya casos en lo que al seleccionar una celda haga miss clicks y no detecte la acción,
  en caso de que ocurra con solo volver a clickear la celda deseada debería seleccionarse y actuar con normalidad.
