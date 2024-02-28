CREATE DATABASE IF NOT EXISTS `netflixDB`;
       USE netflixDB;
CREATE TABLE `usuarios` (
                            `id` int AUTO_INCREMENT PRIMARY KEY,
                            `nombre` varchar(255) NOT NULL,
                            `email` varchar(255) NOT NULL UNIQUE,
                            `contrasena` varchar(255) NOT NULL,
                            `fecha_registro` datetime NOT NULL,
                            `is_admin` bool NOT NULL
);

CREATE TABLE `peliculas` (
                             `id` int AUTO_INCREMENT PRIMARY KEY,
                             `titulo` varchar(255) NOT NULL,
                             `lanzamiento` int NOT NULL,
                             `actor` varchar(255) NOT NULL,
                             `autor` varchar(255) NOT NULL,
                             `productor` varchar(255) NOT NULL,
                             `descripcion` varchar(255) NOT NULL,
                             `imagen` varchar(255) NOT NULL,
                             `url` varchar(255) NOT NULL
);

CREATE TABLE `categorias` (
                              `id` int AUTO_INCREMENT PRIMARY KEY,
                              `nombre` varchar(255) NOT NULL,
                              `descripcion` varchar(255) NOT NULL
);

CREATE TABLE `peliculas_categorias` (
                                        `pelicula_id` int NOT NULL,
                                        `categoria_id` int NOT NULL,
                                        PRIMARY KEY (`pelicula_id`, `categoria_id`),  -- Definición de la clave primaria compuesta
                                        FOREIGN KEY (`pelicula_id`) REFERENCES `peliculas` (`id`),
                                        FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id`)
);

CREATE TABLE `comentarios` (
                               `id` int AUTO_INCREMENT PRIMARY KEY,
                               `usuario_id` int NOT NULL,
                               `pelicula_id` int NOT NULL,
                               `comentario` varchar(255) NOT NULL,
                               `fecha_comentario` datetime NOT NULL,
                               FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`),
                               FOREIGN KEY (`pelicula_id`) REFERENCES `peliculas` (`id`)
);

/* Insertar usuarios */
INSERT INTO usuarios (nombre, email, contrasena, fecha_registro, is_admin)
VALUES ('In', 'yasm146@gmail.com', 'yasmi284', NOW(), false),
       ('Otilia', 'otilia@gamil.com', 'otiy24', NOW(), false),
       ('Yessenia', 'yesi34@gamil.com', 'yessse2003', NOW(), false),
       ('Lia', 'li364@gmail.com', '36584lia', NOW(), false),
       ('Briyit', 'Briiy34@gamil.com', 'Brit2833', NOW(), false),
       ('Jhonatan', 'jhon2475@gamil.com', 'Jhon0t37', NOW(), false),
       ('Frank', 'fra35456@gmail.com', 'franki3758', NOW(), false),
       ('Josue', 'Josuu98@gmail.com', 'josue2001', NOW(), false),
       ('Luis', 'lui@gmail.com', '3002luisid', NOW(), false),
       ('IMER', 'im3456@gmail.com', 'imer345', NOW(), false),
       ('Angie', 'angie@gmail.com', 'ani2003', NOW(), false),
       ('Edith', 'Edit456@gmail.com', 'ed4hto', NOW(), false),
       ('Jhon', 'jho38u@gmail.com', 'jhose2004', NOW(), false),
       ('Erick', 'erick34@gmail.com', 'erick2006', NOW(), false),
       ('Jhair', 'jhai349@gmail.com', 'jhai7995', NOW(), false),
       ('Marlon', 'marlin3849@gmail.com', 'marlin2005', NOW(), false),
       ('Ivan', 'Ivan5369097@gmail.com', 'Ivanyi3394', NOW(), false),
       ('Moises', 'Mois3452009@gmail.com', '2005mai', NOW(), false),
       ('Richar', 'rich@gmail.com', '2007mirsh', NOW(), false),
       ('Carlos', 'carlit91@gmail.com', 'carlos2005', NOW(), false),
       ('Ismael', 'Isma274@gmail.com', 'ismael385', NOW(), false),
       ('jhon', 'jhomti@gmail.com', 'jhon2002', NOW(), true)
;


/* Insertar peliculas */
INSERT INTO peliculas (titulo, lanzamiento, actor, autor, productor, descripcion, imagen, url)
VALUES ('Avatar', 2009, 'Sam Worthington, Zoe Saldana', 'James Cameron', 'James Cameron', 'Un ex marine paracaidista se convierte en el líder de una tribu nativa de Pandora para luchar contra una corporación que está extrayendo un mineral valioso.', 'avatar.png', 'https://www.youtube.com/embed/pPEm5agwjS8?si=JFLpcnGbZHrh5lVN'),
       ('Vengadores: Endgame', 2019, 'Robert Downey Jr., Chris Evans, Scarlett Johansson', 'Christopher Markus, Stephen McFeely', 'Kevin Feige', 'Los Vengadores viajan en el tiempo para recolectar las Piedras Infinitas y derrotar a Thanos.', 'endgame.png', 'https://www.youtube.com/embed/EwPgTSxrIwc?si=IsaynK7u47uj-po9'),
       ('Los Vengadores', 2012, 'Robert Downey Jr., Chris Evans, Mark Ruffalo', 'Joss Whedon', 'Kevin Feige', 'Los Vengadores se unen para detener a Loki de apoderarse de la Tierra.', 'eraUltron.png', 'https://www.youtube.com/embed/ez_w30eWzzQ?si=XEbxr_VzEW2U2GY-'),
       ('Jurassic World', 2015, 'Chris Pratt, Bryce Dallas Howard', 'Colin Trevorrow', 'Steven Spielberg', 'Un nuevo parque temático de dinosaurios se abre en la isla Nublar, pero los dinosaurios escapan y causan estragos.', 'jurassicWorld.png', 'https://www.youtube.com/embed/LnwRvF4aoBg?si=SgU6T2OV4YCnVnS4'),
       ('Titanic', 1997, 'Leonardo DiCaprio, Kate Winslet', 'James Cameron', 'James Cameron', 'Una pareja de diferentes clases sociales se enamora a bordo del Titanic, un transatlántico que se hunde en su viaje inaugural.', 'titanic.png', 'https://www.youtube.com/embed/a3jrDmZqEUA?si=TAHSdYNtVyRY3FLq'),
       ('Star Wars: Episodio VII - El despertar de la fuerza', 2015, 'Harrison Ford, Mark Hamill, Carrie Fisher', 'J.J. Abrams', 'Kathleen Kennedy', 'Treinta años después de la caída del Imperio Galáctico, un grupo de rebeldes se enfrenta a la Primera Orden, una nueva organización que amenaza la paz en la galaxia.', 'starWarsBestias.png', 'https://www.youtube.com/embed/7x6ksE02L-o?si=l_srosM7xUNzkjUP'),
       ('Spider-Man: No Way Home', 2021, 'Tom Holland, Zendaya, Benedict Cumberbatch', 'Jon Watts', 'Kevin Feige', 'Peter Parker pide ayuda a Doctor Strange para que su identidad secreta se mantenga en secreto, pero el hechizo sale mal y villanos de otros universos llegan a la Tierra.', 'noWayHome.png', 'https://www.youtube.com/embed/bsy-D3XBoEY?si=n56jWdBdYLmMKrfk'),
       ('El Senor de los Anillos: La comunidad del anillo', 2001, 'Elijah Wood, Ian McKellen, Viggo Mortensen', 'Peter Jackson', 'Peter Jackson', 'Un hobbit llamado Frodo se embarca en una misión para destruir el Anillo Único, un poderoso artefacto que podría destruir la Tierra Media.', 'comunidadDelAnillo.png', 'https://www.youtube.com/embed/gHwkYqdvbXg?si=exi-E2hrLBPoPWo2'),
       ('Fast and Furious 9', 2021, 'Vin Diesel, Michelle Rodriguez, Tyrese Gibson', 'Justin Lin', 'Chris Morgan', 'Dominic Toretto y su familia se enfrentan a un villano que quiere vengarse de ellos.', 'fastAndForious.png', 'https://www.youtube.com/embed/FUK2kdPsBws?si=v5V2s43S4TfcMKIE'),
       ('Vengadores: Infinity War', 2018, 'Robert Downey Jr., Chris Evans, Scarlett Johansson', 'Anthony Russo, Joe Russo', 'Kevin Feige', 'Thanos, un titán loco, reúne las seis Piedras Infinitas para destruir la mitad de la vida en el universo.', 'infityWar.png', 'https://www.youtube.com/embed/alpPytLEoWU?si=k9hT1OS0vse0nA4t'),
       ('Spider-Man: Lejos de casa', 2019, 'Tom Holland, Zendaya, Jake Gyllenhaal', 'Jon Watts', 'Kevin Feige', 'Peter Parker viaja a Europa con sus amigos para unas vacaciones, pero se ve envuelto en una conspiración internacional.', 'lejosDeCasa.png', 'https://www.youtube.com/embed/iQ8MbSLigsE?si=vRtUbngfYgNyJBRD'),
       ('El rey leon', 2019, 'Donald Glover, Beyoncé, Seth Rogen', 'Jon Favreau', 'Jon Favreau', 'Simba, un joven león, debe reclamar su lugar como rey de la sabana.', 'reyLeon.png', 'https://www.youtube.com/embed/ocgwjiALsKw?si=nK0IkWBB4ni8fJam'),
       ('Aquaman', 2018, 'Jason Momoa, Amber Heard, Willem Dafoe', 'James Wan', 'Peter Safran', 'Arthur Curry, un mestizo humano-atlanteano, debe defender a Atlantis de un villano que quiere destruirla.', 'aquaman.png', 'https://www.youtube.com/embed/r0owse1u8KI?si=yfHc7O3cX6W9zoJ2'),
       ('Los Minions', 2015, 'Pierre Coffin, Steve Carell', 'Kyle Balda, Pierre Coffin', 'Chris Meledandri', 'Los Minions, unos pequeños seres amarillos, buscan un nuevo amo a quien servir.', 'minions.png', 'https://www.youtube.com/embed/JTK_0XY_APg?si=i73y-Q-lrJzLpWvO'),
       ('Capitana Marvel', 2019, 'Brie Larson, Samuel L. Jackson, Jude Law', 'Anna Boden, Ryan Fleck', 'Kevin Feige', 'Carol Danvers, una piloto de la Fuerza Aérea de los Estados Unidos, se convierte en una superheroína después de ser expuesta a una energía alienígena.', 'capitanaMarvel.png', 'https://www.youtube.com/embed/MJIz2gf3Wa8?si=VmtRmLfecsICttSg'),
       ('Toy Story 4', 2019, 'Tom Hanks, Tim Allen, Annie Potts', 'Josh Cooley', 'Mark Nielsen', 'Woody, Buzz Lightyear y sus amigos deben ayudar a Bonnie a encontrar una nueva amiga.', 'toyStore.png', 'https://www.youtube.com/embed/p5_AOAnp7iM?si=gsmqwzZrZOC6ez2F'),
       ('Jurassic World: El reino caido', 2018, 'Chris Pratt, Bryce Dallas Howard, Jeff Goldblum', 'J.A. Bayona', 'Steven Spielberg', 'La película muestra a los dinosaurios en una serie de ciencia ficción.', 'elReinoCaido.png', 'https://www.youtube.com/embed/BXp3wZ5z4iU?si=aaqZ3Xo7q4YGQz4d'),
       ('Hacker - Todo Crimen Tiene Un Inicio', 2002, 'Callan McAuliffe', 'Lorraine Nicholson', 'Akan Satayev', 'En busca de soluciones financieras, se adentra en el crimen en línea con la ayuda de Sye. Robo de identidades y Dark Web lo llevan a un enfrentamiento con Anonymous.', 'hacker.png', 'https://www.youtube.com/embed/eq1s_z0BmHc?si=ixi00XaFBS7PpE3E');

/* Insertar categorias */
INSERT INTO categorias (nombre, descripcion)
VALUES ('Acción', 'Películas llenas de adrenalina y emociones fuertes.'),
       ('Aventura', 'Historias que exploran mundos nuevos y desafiantes.'),
       ('Ciencia Ficción', 'Narrativas que se desarrollan en un contexto futurista o ficticio.'),
       ('Comedia', 'Películas que buscan hacerte reír y disfrutar momentos ligeros.'),
       ('Drama', 'Historias profundas y emocionales que buscan conmover.'),
       ('Fantasía', 'Mundos de imaginación y magia.'),
       ('Suspenso', 'Películas que mantienen al espectador en tensión constante.'),
       ('Romance', 'Historias de amor y relaciones sentimentales.'),
       ('Animación', 'Películas dibujadas o generadas por computadora para todas las edades.');

/* Insertar peliculas_categorias */
INSERT INTO peliculas_categorias (pelicula_id, categoria_id)
VALUES
    (1, 3),  -- Avatar - Ciencia Ficción
    (2, 1),  -- Vengadores: Endgame - Acción
    (2, 3),  -- Vengadores: Endgame - Ciencia Ficción
    (3, 1),  -- Los Vengadores - Acción
    (4, 1),  -- Jurassic World - Acción
    (5, 9),  -- Titanic - Animación (hipotético)
    (6, 3),  -- Star Wars: Episodio VII - El despertar de la fuerza - Ciencia Ficción
    (7, 1),  -- Spider-Man: No Way Home - Acción
    (7, 8),  -- Spider-Man: No Way Home - Romance (hipotético)
    (8, 3),  -- El Senor de los Anillos: La comunidad del anillo - Ciencia Ficción
    (9, 1),  -- Fast and Furious 9 - Acción
    (10, 3), -- Vengadores: Infinity War - Ciencia Ficción
    (11, 1), -- Spider-Man: Lejos de casa - Acción
    (11, 2), -- Spider-Man: Lejos de casa - Aventura (hipotético)
    (12, 9), -- El rey leon - Animación
    (13, 1), -- Aquaman - Acción
    (14, 9), -- Los Minions - Animación
    (15, 3), -- Capitana Marvel - Ciencia Ficción
    (16, 9), -- Toy Story 4 - Animación
    (17, 3), -- Jurassic World: El reino caido - Ciencia Ficción
    (18, 1)  -- Hacker - Todo Crimen Tiene Un Inicio - Acción
;
/* Insertar comentarios */
INSERT INTO comentarios (usuario_id, pelicula_id, comentario, fecha_comentario)
VALUES
    (1, 1, '¡Gran película! Me encantó la aventura en Pandora.', '2023-05-12 15:30:00'),
    (2, 2, 'El final fue increíble, ¡Thanos no tuvo oportunidad!', '2023-08-28 18:45:00'),
    (3, 5, 'Siempre lloro con esta película, ¡es hermosa!', '2022-10-05 21:10:00'),
    (4, 11, 'Una de las mejores películas de Spider-Man, ¡mucha acción!', '2023-11-19 12:20:00'),
    (5, 16, 'Toy Story nunca decepciona, ¡qué nostalgia!', '2023-02-09 09:55:00'),
    (6, 18, '¡Una película que muestra la realidad del mundo cibernético!', '2024-01-02 17:00:00')
;

ALTER TABLE peliculas
    MODIFY COLUMN url VARCHAR(1000) NOT NULL;
ALTER TABLE peliculas
    MODIFY COLUMN descripcion VARCHAR(2000) NOT NULL;