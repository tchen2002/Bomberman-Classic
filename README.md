# Bomberman-Classic


## Resumen Ejecutivo
Para este proyecto se pidió desarrollar el juego Bomberman con algunas
variantes, el juego consiste en que tenemos a nuestro héroe y también varios
villanos que estos van a variar según el nivel en que se encuentre. El Héroe
debe matar a todos los villanos y encontrar la puerta para el siguiente nivel
antes de perder una vida o que se acabe el tiempo.
El héroe esta vez se mueve igual con las flechas del teclado pero ofrece
más funciones ya que puede pausar el juego con la tecla enter, con el mouse
puede poner una bomba o detonarla y con la tecla Shift o Spacebar se coloca
una bomba en el tablero. El héroe tiene 3 vidas y 200 segundos para ganar
el juego después de ese tiempo se mostrará en pantalla un mensaje de game
over. Pero si pierde una vida el tiempo se vuelve a reiniciar. Los villanos
siguen una distribución de Poisson y en el caso del mapa, los ladrillos y
muros de acero se colocan aleatoriamente, siendo la probabilidad de haber
un muro dada por el archivo de configuración.
Para este proyecto en la documentación no vendrá agregado el análisis
de pruebas, pero se incluirá un diagrama de clases donde resume cómo desa-
rrollamos el juego. Se desarrolló en Java y utilizamos la biblioteca Swing ya
que esta nos facilita mostrar en pantalla el juego con sus personajes y poder
utilizar caracterı́sticas como el reconocimiento del teclado o mouse. Utiliza-
mos un Jdk 8 y otra biblioteca utilizada es Javazoom para las funciones del
sonido.

## Installation - Intellij
El IDE utilizado para desarrollar el juego fue Intellij, los pasos para instalar Intellij en Ubuntu son los siguientes:

    1. sudo snap install intellij-idea-community --classic
     
    2. sudo snap install intellij-idea-community --classic --edge
    
    
Para ejecutar el proyecto se debe incluir jl1.0.jar que es un paquete que permite reproducir sonidos en formato MP3. El archivo jl1.0.jar se encuentra en la carpeta del proyecto.
	

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
