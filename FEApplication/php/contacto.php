<?php



$nombre=$_POST["nombre"];
$apellido=$_POST["apellido"];
$facultad=$_POST["facultad"];
$email=$_POST["email"];
$message=$_POST["message"];


echo "message".$message;



$enlace =  mysql_connect('localhost', 'root', 'd3zinf04m');
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
         
         $consulta =  "insert into t_contacto(nombre_cont,apellido_cont,email_cont,facultad_cont,mensaje_cont)values('".$nombre."','".$apellido."','".$email."','".$facultad."','".$message."')";
          
              echo "Consulta : ".$consulta;

          // Ejecutar la consulta
         $resultado = mysql_query($consulta,$enlace);

           if (!$resultado) {
           	echo " consulta : "+$consulta;
            $mensaje  = "Consulta no válida: " . mysql_error() . "\n";
            $mensaje .= "Consulta completa: " . $consulta;
           die($mensaje);
          }else
          {
          	echo "Se ejecuto la consulta";

          }
            
        

      }
  }

mysql_close($enlace);



?>