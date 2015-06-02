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
         //$consulta =  "select * from  t_comedor ";
          $consulta="select * from comedor where  fecha >= DATE(NOW()) order by fecha limit 0, 6";
		  
             

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


                   $row_array["com_id"] = $row["com_id"];
                   $row_array["com_nombre"] = $row["com_nombre"];
                   $row_array["com_lat"] = $row["com_lat"];
                   $row_array["com_long"]  =  $row["com_long"];
                   $row_array["com_direccion"] = $row["com_direccion"];
                    $row_array["com_responsable"]  =  $row["com_responsable"];
                   
                   array_push($arr,$row_array);
  
                }
          


               echo json_encode($arr);

           } 
        
          }
            
        

      }
  }

mysql_close($enlace);

?>