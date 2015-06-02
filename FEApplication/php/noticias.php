<?php 


require_once ("config.php");

$enlace =  mysql_connect($servidor, $user,$password);
if (!$enlace) {
    die('No pudo conectarse: ' . mysql_error());
}
else
	{ 
	// Hacer que foo sea la base de datos actual
      $bd_seleccionada = mysql_select_db('base02', $enlace);
      if (!$bd_seleccionada) {
         die ('No se puede usar foo : ' . mysql_error());
      }else
      {
         
         $consulta =  "select Id,titulo,bajada,fecha,foto,priparrafo,segparrafo,terparrafo,volanta from  noticias where publicado <> 0 and (publicado=1 or publicado=2) order by Id desc limit 5  ";
          
		 //$consulta="select * from noticias where Id=96"; 
             

          // Ejecutar la consulta
         $resultado = mysql_query($consulta,$enlace);

           if (!$resultado) {
           	echo " consulta : "+$consulta;
            $mensaje  = "Consulta no válida: " . mysql_error() . "\n";
            $mensaje .= "Consulta completa: " . $consulta;
           die($mensaje);
          }else
          {
          	 $arr=array();
          

             $total_records = mysql_num_rows($resultado);

             if($total_records >= 1)
             {

             	
             while ($row = mysql_fetch_array($resultado, MYSQL_ASSOC))

                {

                   $row_array["noticia_id"] = $row["Id"];
                   $row_array["noticia_titulo"] = $row["titulo"];
                   $row_array["noticia_bajada"] = $row["bajada"];
                   $row_array["noticia_fecha"]  =  $row["fecha"];
                   $row_array[ "noticia_url_image"] =  $row["foto"];
                   $row_array[ "noticia_cuerpo"] =  $row["priparrafo"] . "\n" . 
                                                    $row["segparrafo"] . "\n"  . 
                                                    $row["terparrafo"] ; 
                   array_push($arr,$row_array);
  
                }
          


               echo json_encode($arr);

           } 
        
          }
            
        

      }
  }

mysql_close($enlace);



?>


