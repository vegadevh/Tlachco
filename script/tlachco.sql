CREATE TABLE ROL(
	id_rol SERIAL PRIMARY KEY NOT NULL,
	rol VARCHAR(20)
);

CREATE TABLE USUARIO(
	usuario VARCHAR(50) UNIQUE PRIMARY KEY,
	password TEXT,
	enabled_u BOOL,
	nombre VARCHAR(80),
	apellido VARCHAR(80),
	id_rol INTEGER,
	FOREIGN KEY(id_rol) REFERENCES ROL(id_rol)
);

CREATE TABLE CATEGORIA(
    id_categoria SERIAL PRIMARY KEY NOT NULL,
    categoria VARCHAR(30)
);
-- archivos/infografias
CREATE TABLE ARCHIVO(
    id_archivo TEXT PRIMARY KEY NOT NULL,
    contenido BYTEA,
    tipo TEXT,
    nombre TEXT
);
-- noticias/articulos/infografias/archivos
CREATE TABLE PUBLICACION(
    id_publicacion SERIAL PRIMARY KEY NOT NULL,
    titulo VARCHAR(200),
    contenido TEXT,
    fecha_publicacion DATE,
    estado VARCHAR(10),
    id_categoria INTEGER,
    propietario VARCHAR(50),
    id_archivo TEXT,
    FOREIGN KEY(id_categoria) REFERENCES CATEGORIA(id_categoria),
    FOREIGN KEY(propietario) REFERENCES USUARIO(usuario),
    FOREIGN KEY(id_archivo) REFERENCES ARCHIVO(id_archivo)
);

CREATE TABLE TAG(
    id_tag SERIAL PRIMARY KEY NOT NULL,
    tag VARCHAR(30),
    id_publicacion INTEGER,
    FOREIGN KEY(id_publicacion) REFERENCES PUBLICACION(id_publicacion)
);
-- likes
CREATE TABLE REACCION(
    id_reaccion SERIAL PRIMARY KEY NOT NULL,
    id_publicacion INTEGER,
    usuario VARCHAR(50),
    FOREIGN KEY(id_publicacion) REFERENCES PUBLICACION(id_publicacion),
    FOREIGN KEY(usuario) REFERENCES USUARIO(usuario)
);

CREATE TABLE COMENTARIO(
    id_comentario SERIAL PRIMARY KEY NOT NULL,
    comentario VARCHAR(500),
    id_publicacion INTEGER,
    usuario VARCHAR(50),
    FOREIGN KEY(id_publicacion) REFERENCES PUBLICACION(id_publicacion),
    FOREIGN KEY(usuario) REFERENCES USUARIO(usuario)
);
