/**
 * 
 */

function valida(){
	let rg = cadrastro.rg.value
	let cpf = cadrastro.cpf.value
	
	
	if(rg===""){
		alert("Preenchar o campo RG")
		cadrastro.nome.focus()
		return false
		
	}
	
	else if(cpf===""){
		alert("Preenchar o campo CPF")
		cadrastro.fone.focus()
		return false
	}else{
		
	
	    document.forms["cadrastro"].submit();
       	alert('Dados cadrastrador com sucesso!')
	
	 }
	
	
}


 function alerta(){
	alert('ola mundo')
}