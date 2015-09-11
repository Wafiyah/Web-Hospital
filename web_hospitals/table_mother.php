<?php 

	$conn = oci_connect('DB_PROJECT', 'dbproject', 'localhost');
if (!$conn) {
    $e = oci_error();
    trigger_error(htmlentities($e['message'], ENT_QUOTES), E_USER_ERROR);
} else {
    echo " none ";
}

$query = 'select * from DB_PROJECT.LOGIN_TABLE ';
$stid = oci_parse($conn, $query);
$r = oci_execute($stid);
	
// Fetch each row in an associative array


print '<table border="2" align="center" width="900" >';
   print '<tr><td><p>Dept ID</td><td>  Doctor ID </td><td>Transfer to</td><td>Transferred Amount</td><td>Transfer Date</td><td>User ID</td></tr>';

	while ($row = oci_fetch_row($stid)) {
   foreach ($row as $item) {
       print '<td>'.($item !== null ? htmlentities($item, ENT_QUOTES) : '&nbsp').'</td>';
   }
   print '</tr>';
}
print '</table>';

?>
<html>

</html>