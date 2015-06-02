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
         
         //1 : noticias , 2 :  noticias destacadas 
         //$consulta =  "select * from  noticias where publicado = 3  order by Id desc  limit 5 ";
          $consulta="select * from noticias where publicado = 3 and fecha >= DATE(NOW()) order by fecha limit 0, 6";
		  
             

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


                   $row_array["evento_id"] = $row["Id"];
                   $row_array["evento_titulo"] = $row["titulo"];
                   $row_array["evento_bajada"] = $row["bajada"];
                   $row_array["evento_fecha"]  =  $row["fecha"];
                   $row_array["evento_horario"] = "";
                   $row_array["evento_cuerpo"] =  $row["priparrafo"] . "\n" . 
                                                    $row["segparrafo"] . "\n"  . 
                                                    $row["terparrafo"] ; 
                   $row_array["evento_url_web"]=""; 


                   array_push($arr,$row_array);
  
                }
          


               echo json_encode($arr);

           } 
        
          }
            
        

      }
  }

mysql_close($enlace);

?>