package com.cep;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
//import java.util.Scanner;
import net.sf.json.JSONObject;
import javax.swing.JOptionPane;



public class ApiCep {

    //chamada da url 
    public String chamaUrl(String url) throws IOException {

        URL viacep = new URL(url);
        BufferedReader bufferedReader = new BufferedReader(
          new InputStreamReader(viacep.openStream()));
  
        String retornoJson;
  
        StringBuilder builder = new StringBuilder();
        while ((retornoJson = bufferedReader.readLine()) != null)
          builder.append(retornoJson);
  
        bufferedReader.close();
  
        return builder.toString();
      }

      // Recebedor dos valores do JSON
    private static JSONObject chamadaHttp() throws IOException {
        
        //input do cep
        String Vcep = JOptionPane.showInputDialog("Informe o CEP: ");

		ApiCep chamada = new ApiCep();
		String retornoJson = chamada.chamaUrl("https://viacep.com.br/ws/"+Vcep+"/json/");
		JSONObject objetoJson = JSONObject.fromObject(retornoJson);

		return objetoJson;
	}

    //Declacao de variaveis

    private String cep;
    public String getcep(){ return cep; }
    public void setcep(String cep){ this.cep =cep;}

    private String uf;
    public String getuf(){ return uf; }
    public void setuf(String uf){ this.uf =uf;}

    private String logradouro;
    public String getlogradouro(){ return logradouro; }
    public void setlogradouro(String logradouro){ this.logradouro =logradouro;}

    private String bairro;
    public String getbairro(){ return bairro; }
    public void setbairro(String bairro){ this.bairro =bairro;}
    
    private String localidade;
    public String getlocalidade(){ return localidade; }
    public void setlocalidade(String localidade){ this.localidade =localidade;}
    

    @Override
    public String toString() {
        //odeio interface javaaaaaaa
         JOptionPane.showMessageDialog(null,"Dados do cep: \n cep: "+ cep +" \n lougradouro: "+logradouro+" \n bairro: "+bairro+"\n Cidade: "+localidade+" \n uf: "+uf);

         return null;
    }

    //Main do projeto

    public static void main(String[] args) throws IOException {

		//Retorno JSON

		JSONObject object = chamadaHttp();
		//System.out.println(object);

		//Retorno dos dados que precisamos

		String cep = (String) object.get("cep");
        String uf = (String) object.get("uf");
        String bairro = (String) object.get("bairro");
        String localidade = (String) object.get("localidade");
        String logradouro = (String) object.get("logradouro");

		//Populando o objeto viacep para persistencia
		ApiCep Viacep = new ApiCep();
		Viacep.setcep(cep);
        Viacep.setuf(uf);
        Viacep.setlogradouro(logradouro);
        Viacep.setbairro(bairro);
        Viacep.setlocalidade(localidade);

		//Impressão do objeto
		System.out.println(Viacep);
	}

    
}
