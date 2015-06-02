<?php 


require_once ("config.php");



require_once ("config.php");

$idNew= $_GET["idEvent"];


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
          
          if($idNew=="null")
          {
         $consulta =  "select Id,titulo,bajada,fecha,foto,priparrafo,segparrafo,terparrafo,volanta from  noticias where publicado <> 0 and (publicado=1 or publicado=2) order by Id desc limit 10  ";
          
          }
          else
          {
            $consulta="select Id,titulo,bajada,fecha,foto,priparrafo,segparrafo,terparrafo,volanta from  noticias where publicado <> 0 and (publicado=1 or publicado=2) and id < ".$idNew ." order by Id desc limit 10  ";
              
          }
          

  // Hacer que foo sea la base de datos actual
      $bd_seleccionada = mysql_select_db('base02', $enlace);
      if (!$bd_seleccionada) {
         die ('No se puede usar foo : ' . mysql_error());
      }else
      {
         
         //1 : noticias , 2 :  noticias destacadas 
         //$consulta =  "select * from  noticias where publicado = 3  order by Id desc  limit 5 ";
           

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


                   $row_array["event_idEvent"] = $row["Id"];
                   $row_array["event_name"] = $row["titulo"];
                   $row_array["event_username"] = "manuonda";
                   $row_array["event_urlImageDate"]  = "imagen";
                   $row_array["event_message"] = "Consulta de fisica 2 chicos hoy a 16 hs";
                   $row_array["event_date"] =  "14/04/2015";
                   

                   array_push($arr,$row_array);
  
                }
          


               echo json_encode($arr);

           } 
        
          }
            
        

      }
  }
    }

mysql_close($enlace);

?>