BLOB RUN

Proyecto Integrador - Desarrollo de Aplicaciones Móviles

Semestre: 4 A DSM
Fecha de entrega: 11 de Diciembre 2025

Equipo de Desarrollo
Nombre Completo	Rol /Tareas Principales	Usuario GitHub
Renata Lizbeth Ortiz Chávez
	Organización del equipo, Servidor, Login y Registro	
    @Ren1989lisa
Valentino Amador Gonzales Adames	
    Interfaz Play, Editar Usuario, Dependencias del Giroscopio
    @Valentinoxrl8
Jacqueline Pilgram Montes de Oca	
    Interfaces TopPlay, GameMenu, Conexión entre Pantallas
    @pilgram2393


Descripción del Proyecto
¿Qué hace la aplicación?
BLOB RUN es un juego móvil inspirado en mecánicas tipo Pou Jump, donde el jugador controla un personaje que debe saltar entre plataformas mientras recolecta objetos y evita caer.
El movimiento del personaje se controla inclinando el dispositivo gracias al uso del sensor giroscopio, haciendo que la experiencia sea más interactiva y dinámica.
La aplicación incluye autenticación (login/registro), perfiles de usuario, marcador de puntajes y un sistema CRUD conectado a un servidor mediante una API REST hecha con Retrofit.
A usuarios casuales que desean un juego sencillo, divertido y con controles intuitivos por movimiento.

Objetivo:
Demostrar una arquitectura robusta MVVM utilizando Jetpack Compose, Retrofit y sensores del dispositivo mientras se desarrolla un videojuego funcional con pantallas conectadas y operaciones completas con servidor.


Stack Tecnológico y Características

Este proyecto ha sido desarrollado siguiendo estrictamente los lineamientos de la materia:
Lenguaje: Kotlin 100%.
Interfaz de Usuario: Jetpack Compose.
Arquitectura: MVVM (Model-View-ViewModel).
Conectividad (API REST): Retrofit.
GET: Obtiene datos del usuario, puntajes y progreso del juego.
POST: Registra nuevos usuarios o puntajes.
UPDATE (PUT/PATCH): Actualiza datos de usuario o cambios en su perfil.
DELETE: Elimina perfiles o información del usuario almacenada.
Sensores Integrados: Giroscopio y Acelerómetro.
Uso:
El giroscopio detecta rotación y se usa para movimientos suaves del personaje.
El acelerómetro detecta inclinación del dispositivo para mover al personaje a la izquierda o derecha durante el juego.


Capturas de Pantalla

[Coloca al menos 3 (investiga como agregarlas y se vean en GitHub)]





Instalación y Releases

El ejecutable firmado (.apk) se encuentra disponible en la sección de **Releases** de este repositorio.
https://github.com/ValentinoXRL8/Salta_Salta/releases/tag/Salta_A_4_0.1
sha256:27e9a5eb61f2895d7485b0c712e65d70d8e39d512080f14b176c294f1e344c26

