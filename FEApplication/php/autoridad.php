<?php 


require_once ("config.php");

$dir_fotos="http://www.unju.edu.ar/php/fotos/facebook.jpg";




$arr=array(

 array(
  "autoridad_id"=>"1",
  "autoridad_titulo"=>"RECTOR",
  "autoridad_nombre"=>"Lic. Rodolfo Alejandro TECCHI ",
  "autoridad_email"=>"rtecchi@unju.edu.ar",
  "autoridad_telefono"=>"Tel: +54-(388)-4221-502 ",
  "autoridad_url_imagen"=>$dir_fotos
  ),

 array(
  "autoridad_id"=>"2",
  "autoridad_titulo"=>"VICE-RECTOR",
  "autoridad_nombre"=>"Lic. Jorge Eugenio GRIOT ",
  "autoridad_email"=>"viceerrector@unju.edu.ar ",
  "autoridad_telefono"=>"Tel: +54-(388)-4221-501 ",
  "autoridad_url_imagen"=>$dir_fotos
  ),

 array(
  "autoridad_id"=>"3",
  "autoridad_titulo"=>"SECRETARIO GENERAL",
  "autoridad_nombre"=>"E.S. Edgardo Aramayo ",
  "autoridad_email"=>"secgeneral@unju.edu.ar ",
  "autoridad_telefono"=>"Tel: +54-(388)-4221-517 ",
  "autoridad_url_imagen"=>$dir_fotos
  ),

 array(
  "autoridad_id"=>"4",
  "autoridad_titulo"=>"SEC. LEGAL Y TECNICO",
  "autoridad_nombre"=>"Dr. César Guillermo FARFÁN ",
  "autoridad_email"=>"gfarfan@unju.edu.ar ",
  "autoridad_telefono"=>"Tel: +54-(388)-4221-517 ",
  "autoridad_url_imagen"=>$dir_fotos
  ),
 array(
  "autoridad_id"=>"5",
  "autoridad_titulo"=>"SEC. DE ADMINISTRACION",
  "autoridad_nombre"=>"CPN Fernanda COLQUE ",
  "autoridad_email"=>"secadmin@unju.edu.ar ",
  "autoridad_telefono"=>"Tel: +54-(388)-4221-514 ",
  "autoridad_url_imagen"=>$dir_fotos
  ),
 array(
  "autoridad_id"=>"6",
  "autoridad_titulo"=>"SEC. DE CIENCIA Y TECNICA",
  "autoridad_nombre"=>"Dra. María G. BOVI MITRE ",
  "autoridad_email"=>"secretariasectergb@unju.edu.ar ",
  "autoridad_telefono"=>"Tel: +54-(388)-4221-505 ",
  "autoridad_url_imagen"=>$dir_fotos
  ),
  array(
  "autoridad_id"=>"7",
  "autoridad_titulo"=>"SEC. DE EXTENSION UNIVERSITARIA",
  "autoridad_nombre"=>"Dra. Elena Ester BELLI ",
  "autoridad_email"=>"seu@unju.edu.ar ",
  "autoridad_telefono"=>"el: +54-(388)-4244-100 ",
  "autoridad_url_imagen"=>$dir_fotos
  ),
   array(
  "autoridad_id"=>"8",
  "autoridad_titulo"=>"SEC. DE ASUNTOS ACADEMICOS",
  "autoridad_nombre"=>"Dr. Julio César ARRUETA ",
  "autoridad_email"=>"c.arrueta@unju.edu.ar ",
  "autoridad_telefono"=>"Tel: +54-(388)-4221-504 ",
  "autoridad_url_imagen"=>$dir_fotos
  ),
  array(
  "autoridad_id"=>"9",
  "autoridad_titulo"=>"SEC. DE BIENESTAR UNIVERSITARIO",
  "autoridad_nombre"=>"Sr. Diego Esteban GUTIERREZ ",
  "autoridad_email"=>"sbu@unju.edu.ar ",
  "autoridad_telefono"=>"Tel: +54-(388)-4221-500 ",
  "autoridad_url_imagen"=>$dir_fotos
  ),
   array(
  "autoridad_id"=>"10",
  "autoridad_titulo"=>"DECANO - FACULTAD DE INGENIERIA",
  "autoridad_nombre"=>"Ing. Gustavo Alberto Lores ",
  "autoridad_email"=>"decano@fi.unju.edu.ar  ",
  "autoridad_telefono"=>"Tel: +54-(388)-4221-542 ",
  "autoridad_url_imagen"=>$dir_fotos
  ),
     array(
  "autoridad_id"=>"11",
  "autoridad_titulo"=>"DECANO - FACULTAD DE HUMANIDADES Y CIENCIAS SOCIALES",
  "autoridad_nombre"=>"Dr. Ricardo Enrique Gregorio Slavutsky",
  "autoridad_email"=>"decano@fhycs.unju.edu.ar ",
  "autoridad_telefono"=>"Tel: +54-(388)-4221-560 ",
  "autoridad_url_imagen"=>$dir_fotos
  ),
    array(
  "autoridad_id"=>"12",
  "autoridad_titulo"=>"DECANO - FACULTAD DE CIENCIAS AGRARIAS",
  "autoridad_nombre"=>"Mg. Ing. Agr. Mario Cesar BONILLO ",
  "autoridad_email"=>"decanofca@fca.unju.edu.ar ",
  "autoridad_telefono"=>"Tel: +54-(388)-4221-557 ",
  "autoridad_url_imagen"=>$dir_fotos
  ),
      array(
  "autoridad_id"=>"13",
  "autoridad_titulo"=>"DECANO - FACULTAD DE CIENCIAS ECONOMICAS",
  "autoridad_nombre"=>"CPN María Rosa GRISOLÍA ",
  "autoridad_email"=>"cragrisolia@fce.unju.edu.ar ",
  "autoridad_telefono"=>"Tel: +54-(388)-4221536 ",
  "autoridad_url_imagen"=>$dir_fotos
  ),
        array(
  "autoridad_id"=>"14",
  "autoridad_titulo"=>"DIRECTORA- ESC. DE MINAS ' Dr. Horacio Carrillo'",
  "autoridad_nombre"=>"Lic. Graciela Leticia Medardi",
  "autoridad_email"=>" ",
  "autoridad_telefono"=>"Tel: +54-(388)-4221599/98 ",
  "autoridad_url_imagen"=>$dir_fotos
  )




);

echo json_encode($arr);

?>