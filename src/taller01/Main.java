//Agustín Muñoz Vargas - 21.000.000-K - ICCI

package taller01;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
	
	
	
	public static String[][] ReadArch (String filePath, String divisor, int columnas, int contador)
	{
		int auxContador = contador;
		File arch = new File(filePath);
		String[]   archLinePart 	    = new String[columnas];				//Parte de la línea del archivo.
		String[][] exitData 			= new String[columnas][contador];
		
		try 
		{
			Scanner archLine = new Scanner(arch);	//Línea del archivo.
			while (archLine.hasNextLine() && contador >= 0){
				archLinePart = archLine.nextLine().split(divisor);
				for (int i = 0; i < columnas; i++)
				{
					exitData[i][auxContador-contador] = archLinePart[i];
				}
				contador--;
			}
		} 
		catch (Exception e) 
		{
			System.out.println("[x] Ruta del archivo no encontrado o un problema a la hora de leerlo [Divisor incorrecto o cantidad de columnas incorrectas].");
		}
		return (exitData);
	}



	public static int ContarReal (String[][] valor)
	{
		int exitData = 0;
		for (int i = 0; i < valor[0].length;i++) 
		{	
			if (valor[0][i] == null) 
			{
				continue;
			}
			else 
			{
				exitData++;
			}
		}
		return (exitData);
	}
	
	
	
	public static int ContarAbsolutamenteReal (String[][] valor)
	{
		int exitData = 0;
		for (int i = 0; i < valor[0].length;i++) 
		{	
			if (valor[0][i] == null || valor[0][i].equalsIgnoreCase("ELIMINADO")) 
			{
				continue;
			}
			else 
			{
				exitData++;
			}
		}
		return (exitData);
	}
	
	
	
	public static String GenerateNewSaveArch (String fileName, String dotSomething) 
	{
		try 
		{
			File[] textos = new File("src/reportes/").listFiles(texto -> texto.isFile());
			int auxNumero = 0;
			for (File texto : textos) //Tipo de "for" utilizado en el video [www.youtube.com/watch?v=yF-st0sXU-8], fue el método que encontré para revisar los archivos guardados en la carpeta.
			{
				if ((fileName + auxNumero + dotSomething).equalsIgnoreCase(texto.getName())) 
				{
					auxNumero++;
				}
			}
			FileWriter writerMalvado = new FileWriter("src/reportes/" + fileName + auxNumero + dotSomething);
			writerMalvado.close();
			return (fileName + auxNumero + dotSomething);
			
		} catch (Exception e) {
			System.out.println("[!] No se pudo generar un archivo de guardado.");
			return ("ERROR");
		}
		
	}
	
	
	
	public static void main(String[] args) 
		{
		
		//System.out.println(System.getProperty("user.dir"));
		
		//System.out.println("Raíz Actual: " + new java.io.File(".").getAbsolutePath());
		
		/*
		System.out.println("");
		System.out.println(archAlumnos		[1][1]);
		System.out.println(archSolicitudes	[1][1]);
		*/
		
		int lineScan;
		String lineScanData;
		Scanner scan = new Scanner(System.in);
		int rechazados = 0;
		
		String[][] archAlumnos 		= ReadArch("src/textos/Alumnos.txt"		,";",4,255);
		String[][] archSolicitudes 	= ReadArch("src/textos/Solicitudes.txt"	,"-",2,255);
		String[][] inWhasa 			= new String[4][100]; 
		int archAlumnosReal 	= ContarReal(archAlumnos    );
		int archSolicitudesReal = ContarReal(archSolicitudes);
		int inWhasaReal = ContarReal(inWhasa);
		
		try 
		{
		do 
		{	
			System.out.println("Sistema de Control del Grupo POO");
			System.out.println("1) Cargar archivos (Alumnos y Solicitudes)    [!] [Recarga último guardado de Alumnos.txt y limpia la lista de admitidos en el grupo]");
			System.out.println("2) Procesar solicitudes (Filtrado automatico) [!] [Sobrescribe cambios no guardados del arreglo del grupo de WhatsApp]");
			System.out.println("3) Inscripcion manual al grupo");
			System.out.println("4) Administracion del curso");
			System.out.println("5) Generar reportes");
			System.out.println("6) Analisis estadistico");
			System.out.println("7) Salir");
			System.out.println("");
			System.out.println("Ingrese una opción: ");
			
			lineScan = scan.nextInt();
			System.out.println("");
			
			switch (lineScan) {
				case 1: 
				{
					archAlumnos 		= ReadArch("src/textos/Alumnos.txt"		,";",4,255);
					archSolicitudes 	= ReadArch("src/textos/Solicitudes.txt"	,"-",2,255);
					inWhasa 			= new String[4][100];
					archAlumnosReal 	= ContarReal(archAlumnos    );
					archSolicitudesReal = ContarReal(archSolicitudes);
					System.out.printf("- [O] Alumnos en la lista   : [%d]\n",archAlumnosReal		);
					System.out.printf("- [O] Solicitudes de ingreso: [%d]\n",archSolicitudesReal	);
					break;
				}
				case 2: 
				{
					int contador = 0;
					for (int i = 0; i < archSolicitudesReal; i++) 
					{
						for (int j = 0; j < archAlumnosReal; j++) 
						{
							if (contador > 100)
							{
								System.out.println("- [!] No quedan cupos en el grupo.");
								rechazados++;
								break;
							}
							else if (archSolicitudes[0][i].equalsIgnoreCase(archAlumnos[0][j]) && archSolicitudes[1][i].equalsIgnoreCase(archAlumnos[1][j])) 
							{
								if (contador == 0) 
								{
									inWhasa[0][contador] = archSolicitudes[0][i];
									inWhasa[1][contador] = archSolicitudes[1][i];
									inWhasa[2][contador] = archAlumnos[2][j];
									inWhasa[3][contador] = archAlumnos[3][j];
									System.out.println("- [OK]      " + inWhasa[0][contador] + " " + inWhasa[1][contador] + " -> admitido en " + inWhasa[3][contador]);
									contador++;
								}
								else 
								{
									Boolean isIn = false;
									for (int k = 0; k < contador; k++) 
									{
										if ((archSolicitudes[0][i].equalsIgnoreCase(inWhasa[0][k]) && archSolicitudes[1][i].equalsIgnoreCase(inWhasa[1][k])))
										{
											isIn = true;
											break;
										}
									}
									if (isIn == false)
									{
										inWhasa[0][contador] = archSolicitudes[0][i];
										inWhasa[1][contador] = archSolicitudes[1][i];
										inWhasa[2][contador] = archAlumnos[2][j];
										inWhasa[3][contador] = archAlumnos[3][j];
										System.out.println("- [OK]      " + inWhasa[0][contador] + " " + inWhasa[1][contador] + " -> admitido en " + inWhasa[3][contador]);
										contador++;
									} else {
										rechazados++;
									}	
								}
							}
						}
					}
					inWhasaReal = ContarReal(inWhasa);
					
					/*
					for (int i = 0; i < inWhasaReal; i++)
					{
						System.out.print(inWhasa[0][i] + " ");
						System.out.println(inWhasa[1][i]);
						System.out.println("");
					}
					*/
					
					System.out.println("- Resumen:  " + inWhasaReal + " admitidos / " + (archSolicitudesReal - inWhasaReal) + " rechazados");
					break;
					
				}
				case 3: 
				{
					Boolean isIn;
					String auxNombre;
					String auxApellido;
					String auxRUT;
					String auxParalelo;
					System.out.println("- ¿Cómo desea inscribir a la persona?");
					System.out.println("- 1) Por nombre completo");
					System.out.println("- 2) Por RUT");
					System.out.println("");
					System.out.println("- Ingrese una opción: ");
					
					lineScan = scan.nextInt();
					
					switch(lineScan) {
						case 1:
							isIn = false;
							auxRUT      = null;
							auxParalelo = null;
							archAlumnosReal = ContarReal(archAlumnos);
							
							System.out.println("- - Ingrese nombre: ");
							lineScanData = scan.next();
							auxNombre    = lineScanData;
							
							System.out.println("- - Ingrese apellido: ");
							lineScanData = scan.next();
							auxApellido  = lineScanData;
							
							for (int i = 0; i < archAlumnosReal; i++) 
							{
								if (archAlumnos[0][i].equalsIgnoreCase(auxNombre) && archAlumnos[1][i].equalsIgnoreCase(auxApellido)) 
								{
									auxRUT      = archAlumnos[2][i];
									auxParalelo = archAlumnos[3][i];
									isIn = true;
								}
							}
							if (isIn == false) 
							{
								System.out.println("- - [!] No se encontro este Nombre y/o Apellido.");
								rechazados++;
							}
							else 
							{
								isIn = false;
								inWhasaReal = ContarReal(inWhasa);
								for (int i = 0; i < inWhasaReal; i++) 
								{
									if (inWhasa[0][i].equalsIgnoreCase(auxNombre) && inWhasa[1][i].equalsIgnoreCase(auxApellido)) 
									{
										isIn = true;
									}
								}
								if (isIn == false)
								{
									System.out.println("- - [O] Añadiendo al grupo de WhatsApp...");
									inWhasa[0][inWhasaReal] = auxNombre  ;
									inWhasa[1][inWhasaReal] = auxApellido;
									inWhasa[2][inWhasaReal] = auxRUT     ;
									inWhasa[3][inWhasaReal] = auxParalelo;
								}
								else
								{
									System.out.println("- - [!] Se encontró este Nombre y/o Apellido, pero ya está dentro del grupo de WhatsApp.");
									rechazados++;
								}
							}
							break;

						case 2:
							isIn = false;
							auxNombre   = null;
							auxApellido = null;
							auxParalelo = null;
							archAlumnosReal = ContarReal(archAlumnos);
							
							System.out.println("- - Ingrese RUT: ");
							lineScanData = scan.next();
							auxRUT       = lineScanData;
							
							for (int i = 0; i < archAlumnosReal; i++) 
							{
								if (archAlumnos[2][i].equalsIgnoreCase(auxRUT)) 
								{
									auxNombre   = archAlumnos[0][i];
									auxApellido = archAlumnos[1][i];
									auxParalelo = archAlumnos[3][i];
									isIn = true;
								}
							}
							if (isIn == false) 
							{
								System.out.println("- - [!] No se encontró este RUT.");
								rechazados++;
							}
							else 
							{
								isIn = false;
								inWhasaReal = ContarReal(inWhasa);
								for (int i = 0; i < inWhasaReal; i++) 
								{
									if (inWhasa[2][i].equalsIgnoreCase(auxRUT)) 
									{
										isIn = true;
									}
								}
								if (isIn == false)
								{
									System.out.println("- - [O] Añadiendo al grupo de WhatsApp...");
									inWhasa[0][inWhasaReal] = auxNombre  ;
									inWhasa[1][inWhasaReal] = auxApellido;
									inWhasa[2][inWhasaReal] = auxRUT     ;
									inWhasa[3][inWhasaReal] = auxParalelo;
								}
								else
								{
									System.out.println("- - [!] Ya está en el grupo de WhatsApp.");
									rechazados++;
								}
							}
							
							break;
						default:
							throw new IllegalArgumentException("Valor inesperado: " + lineScan);
					}
					break;
				}
				case 4: 
				{
					System.out.println("- Administración del curso");
					System.out.println("- 1) Cambiar paralelo de un alumno");
					System.out.println("- 2) Eliminar alumno del curso");
					System.out.println("- 3) Inscribir alumno nuevo");
					System.out.println("- 4) Guardar listado en archivo .txt");
					System.out.println("- 5) Volver");
					System.out.println("");
					System.out.println("- Ingrese una opción: ");
					
					lineScan = scan.nextInt();
					System.out.println("");
					
					String auxNombre   = null;
					String auxApellido = null;
					String auxRUT      = null;
					String auxParalelo = null;
					
					switch(lineScan) { 
						case 1:
							auxNombre   = null;
							auxApellido = null;
							archAlumnosReal    = ContarReal(archAlumnos);
							inWhasaReal 	   = ContarReal(inWhasa);
							
							System.out.println("- - Ingrese nombre: ");
							lineScanData = scan.next();
							auxNombre    = lineScanData;
							
							System.out.println("- - Ingrese apellido: ");
							lineScanData = scan.next();
							auxApellido  = lineScanData;
							
							for (int i = 0; i < archAlumnosReal; i++) 
							{
								if (archAlumnos[0][i].equalsIgnoreCase(auxNombre) && archAlumnos[1][i].equalsIgnoreCase(auxApellido)) 
								{
									System.out.println(i);
									if 		  (archAlumnos[3][i].equalsIgnoreCase("C1")) 
									{
										archAlumnos[3][i] = "C2";
										System.out.println("- - [C1] -> [C2]");
									} else if (archAlumnos[3][i].equalsIgnoreCase("C2")) 
									{
										archAlumnos[3][i] = "C1";
										System.out.println("- - [C2] -> [C1]");
									}
									System.out.println("- - " + archAlumnos[0][i] + ";" + archAlumnos[1][i] + ";" + archAlumnos[2][i] + ";" + archAlumnos[3][i]);
								}
							}
							
							for (int i = 0; i < inWhasaReal; i++) 
							{
								if (inWhasa[0][i].equalsIgnoreCase(auxNombre) && inWhasa[1][i].equalsIgnoreCase(auxApellido)) 
								{
									System.out.println(i);
									if 		  (inWhasa[3][i].equalsIgnoreCase("C1")) 
									{
										inWhasa[3][i] = "C2";
										System.out.println("- - [C1] -> [C2]");
									} else if (inWhasa[3][i].equalsIgnoreCase("C2")) 
									{
										inWhasa[3][i] = "C1";
										System.out.println("- - [C2] -> [C1]");
									}
									System.out.println("- - " + inWhasa[0][i] + ";" + inWhasa[1][i] + ";" + inWhasa[2][i] + ";" + inWhasa[3][i]);
								}
							}

							break;
						case 2:
							auxNombre   = null;
							auxApellido = null;
							archAlumnosReal    = ContarReal(archAlumnos);
							inWhasaReal 	   = ContarReal(inWhasa);
							
							System.out.println("- - Ingrese nombre: ");
							lineScanData = scan.next();
							auxNombre    = lineScanData;
							
							System.out.println("- - Ingrese apellido: ");
							lineScanData = scan.next();
							auxApellido  = lineScanData;
							
							for (int i = 0; i < archAlumnosReal; i++) 
							{
								if (archAlumnos[0][i].equalsIgnoreCase(auxNombre) && archAlumnos[1][i].equalsIgnoreCase(auxApellido)) 
								{
									archAlumnos[0][i] = "ELIMINADO";
									archAlumnos[1][i] = "ELIMINADO";
									archAlumnos[2][i] = "ELIMINADO";
									archAlumnos[3][i] = "ELIMINADO";

									System.out.println("- - ELIMINADO" + i);
									System.out.println("- - " + archAlumnos[0][i] + ";" + archAlumnos[1][i] + ";" + archAlumnos[2][i] + ";" + archAlumnos[3][i]);
								}
							}
							
							for (int i = 0; i < inWhasaReal; i++) 
							{
								if (inWhasa[0][i].equalsIgnoreCase(auxNombre) && inWhasa[1][i].equalsIgnoreCase(auxApellido)) 
								{
									inWhasa[0][i] = "ELIMINADO";
									inWhasa[1][i] = "ELIMINADO";
									inWhasa[2][i] = "ELIMINADO";
									inWhasa[3][i] = "ELIMINADO";

									System.out.println("- - ELIMINADO" + i);
									System.out.println("- - " + inWhasa[0][i] + ";" + inWhasa[1][i] + ";" + inWhasa[2][i] + ";" + inWhasa[3][i]);
								}
							}
							break;
						case 3:
							auxNombre   = null;
							auxApellido = null;
							auxRUT		= null;
							auxParalelo = null;
							boolean isIn = false;
							
							archAlumnosReal    = ContarReal(archAlumnos);
							inWhasaReal 	   = ContarReal(inWhasa);
							
							System.out.println("- - Ingrese nombre: ");
							lineScanData = scan.next();
							auxNombre    = lineScanData;
							
							System.out.println("- - Ingrese apellido: ");
							lineScanData = scan.next();
							auxApellido  = lineScanData;
							
							System.out.println("- - Ingrese RUT: ");
							lineScanData = scan.next();
							auxRUT       = lineScanData;
							
							while (0 == 0) 
							{
								System.out.println("- - Ingrese Paralelo [C1 - C2]: ");
								lineScanData = scan.next();
								auxParalelo  = lineScanData;
								if (auxParalelo.equalsIgnoreCase("C1")) 
								{
									auxParalelo = "C1";
									break;
								}
								else if (auxParalelo.equalsIgnoreCase("C2")) 
								{
									auxParalelo = "C2";
									break;
								}
								else 
								{
									System.out.println("- - [!] Ingrese un Paralelo válido entre: [C1 - C2]: ");
								}
							}	
							
							for (int i = 0; i < 100; i++) 
							{
								if (archAlumnos[0][i] == null || archAlumnos[1][i] == null || archAlumnos[2][i] == null || archAlumnos[3][i] == null)
								{
									continue;
								}
								else if (archAlumnos[0][i].equalsIgnoreCase(auxNombre) && archAlumnos[1][i].equalsIgnoreCase(auxApellido) && archAlumnos[2][i].equalsIgnoreCase(auxRUT) && archAlumnos[3][i].equalsIgnoreCase(auxParalelo))
								{
									isIn = true;
								}
							}
							if (isIn == false) 
							{
								for (int i = 0; i < 100; i++) 
								{
									if (archAlumnos[0][i] == (null)) 
									{
										archAlumnos[0][i] = auxNombre  ;
										archAlumnos[1][i] = auxApellido;
										archAlumnos[2][i] = auxRUT     ;
										archAlumnos[3][i] = auxParalelo;
										System.out.println("- - Añadiendo... " + i);
										System.out.println("- - " + archAlumnos[0][i] + ";" + archAlumnos[1][i] + ";" + archAlumnos[2][i] + ";" + archAlumnos[3][i]);
										break;
									}else if (archAlumnos[0][i].equalsIgnoreCase("ELIMINADO")) 
									{
										archAlumnos[0][i] = auxNombre  ;
										archAlumnos[1][i] = auxApellido;
										archAlumnos[2][i] = auxRUT     ;
										archAlumnos[3][i] = auxParalelo;
										System.out.println("- - Añadiendo... " + i);
										System.out.println("- - " + archAlumnos[0][i] + ";" + archAlumnos[1][i] + ";" + archAlumnos[2][i] + ";" + archAlumnos[3][i]);
										break;
									}
								}
							} else 
							{
								System.out.println("[!] Ya esta agregado previamente.");
								System.out.println("");
							}
							/*
							for (int i = 0; i < 100; i++) 
							{
								if (inWhasa[1][i] == (null)) 
								{
									System.out.println("EXISTE");
									inWhasa[0][i] = auxNombre  ;
									inWhasa[1][i] = auxApellido;
									inWhasa[2][i] = auxRUT     ;
									inWhasa[3][i] = auxParalelo;

									System.out.println(i);
									System.out.println(inWhasa[0][i] + " " + inWhasa[1][i] + " " + inWhasa[2][i] + " " + inWhasa[3][i]);
									break;
								}
								else if (inWhasa[0][i].equalsIgnoreCase("ELIMINADO")) 
								{
									System.out.println("EXISTE");
									inWhasa[0][i] = auxNombre  ;
									inWhasa[1][i] = auxApellido;
									inWhasa[2][i] = auxRUT     ;
									inWhasa[3][i] = auxParalelo;

									System.out.println(i);
									System.out.println(inWhasa[0][i] + " " + inWhasa[1][i] + " " + inWhasa[2][i] + " " + inWhasa[3][i]);
									break;
								}
							}
							*/
							break;
						case 4:
							try {
								FileWriter writerAlumnos = new FileWriter("src/textos/Alumnos.txt");
								for (int i = 0; i < 100; i++) 
								{
									if (archAlumnos[0][i] == null || archAlumnos[0][i].equalsIgnoreCase("ELIMINADO")) {
										continue;
									} else {
										String soporteMoral = archAlumnos[0][i] + ";" + archAlumnos[1][i] + ";" + archAlumnos[2][i] + ";" + archAlumnos[3][i] + "\n";
										writerAlumnos.write(soporteMoral);
									}       
								}
								writerAlumnos.close();
							} catch (Exception e) {
								System.out.println("- - [!] Ruta no encontrada");
							}
							break;
						case 5:
							System.out.println("- - [O] Regresando...");
							break;
						default:
							throw new IllegalArgumentException("- - Valor inesperado: " + lineScan);
					}
					break;
				}
				case 5: 
				{
					String newRutaC1 = GenerateNewSaveArch("ReporteC1-V",".txt");
					String newRutaC2 = GenerateNewSaveArch("ReporteC2-V",".txt");
					System.out.println("- Reportes guardados como: [" + newRutaC1 + " ; " + newRutaC2 + "]");
					try {
						FileWriter writerNewSaveC1 = new FileWriter("src/reportes/" + newRutaC1);
						FileWriter writerNewSaveC2 = new FileWriter("src/reportes/" + newRutaC2);
						for (int i = 0; i < 100; i++) 
						{	
							if (inWhasa[3][i] == null || inWhasa[3][i].equalsIgnoreCase("ELIMINADO"))
							{
								continue;
							}
							else if (inWhasa[3][i].equalsIgnoreCase("C1")) 
							{
								String soporteMoral = inWhasa[0][i] + ";" + inWhasa[1][i] + ";" + inWhasa[2][i] + ";" + inWhasa[3][i] + "\n";
								writerNewSaveC1.write(soporteMoral);
							}
							else if (inWhasa[3][i].equalsIgnoreCase("C2")) 
							{
								String soporteMoral = inWhasa[0][i] + ";" + inWhasa[1][i] + ";" + inWhasa[2][i] + ";" + inWhasa[3][i] + "\n";
								writerNewSaveC2.write(soporteMoral);
							}
						}
						writerNewSaveC1.close();
						writerNewSaveC2.close();
					} 
					catch (Exception e) {
						System.out.println("- [!] ERROR DE ESCRITURA");
					}
					break;
				}
				case 6: 

					int auxNumC1 = 0;
					int auxNumC2 = 0;
					
					for (int i = 0; i < 100; i++) 
					{	
						if (inWhasa[3][i] == null || inWhasa[3][i].equalsIgnoreCase("ELIMINADO"))
						{
							continue;
						}
						else if (inWhasa[3][i].equalsIgnoreCase("C1")) 
						{
							auxNumC1++;
						}
						else if (inWhasa[3][i].equalsIgnoreCase("C2")) 
						{
							auxNumC2++;
						}
					}
					System.out.println("Admitidos por paralelo -> C1: " + auxNumC1 + " | C2: " + auxNumC2);
					System.out.println("");
					System.out.println("Nº de Alumnos              : [" + ContarAbsolutamenteReal(archAlumnos)     + "]");
					System.out.println("Nº de Solicitudes          : [" + ContarAbsolutamenteReal(archSolicitudes) + "]");
					System.out.println("Nº de Integrantes del grupo: [" + ContarAbsolutamenteReal(inWhasa)         + "]");
					break;
				case 7: 
				{
					System.out.println("[O] Saliendo...");
					break;
				}
				
				/*
				case 0:
					
					System.out.println("--- Debug Options ---");
					System.out.println("[Este menu fue creado para facilitar el debugeo, como el codigo se sostiene solo por fuerza de voluntad, te recomiendo que no \nle prestes mucha atencion a esto, a menos que quieras generar archivos inutiles porque si]");
					System.out.println("1) Mostrar elementos de archAlumnos");
					System.out.println("2) Mostrar elementos de inWhasa");
					System.out.println("3) Generar un malvado archivo de prueba en [src/textos] >:3");
					System.out.println("4) Volver");
					//System.out.println("5) ");
					System.out.println("");
					System.out.println("Ingrese una opcion: ");
					
					lineScan = scan.nextInt();
					System.out.println("");
					
					switch (lineScan) {
					case 1: 					
						archAlumnosReal = ContarReal(archAlumnos);
						for (int i = 0; i < archAlumnosReal; i++)
						{
							System.out.print("[" + i + "] \t" + archAlumnos[0][i] + " ");
							System.out.print(archAlumnos[1][i] + " ");
							System.out.print(archAlumnos[2][i] + " ");
							System.out.println(archAlumnos[3][i]);
						}
						break;
					case 2:
						inWhasaReal = ContarReal(inWhasa);
						for (int i = 0; i < inWhasaReal; i++)
						{
							System.out.print("[" + i + "] \t" + inWhasa[0][i] + " ");
							System.out.print(inWhasa[1][i] + " ");
							System.out.print(inWhasa[2][i] + " ");
							System.out.println(inWhasa[3][i]);
						}
						break;
					case 3:
					
						try 
						{
							File[] textos = new File("src/textos/").listFiles(texto -> texto.isFile());
							String fileName = "Malvado";
							String dotSomething = ".txt";
							int auxNumero = 0;
							for (File texto : textos) 
							{
								if ((fileName + auxNumero + dotSomething).equalsIgnoreCase(texto.getName())) 
								{
									auxNumero++;
								}
								System.out.println(texto.getName());
							}
							FileWriter writerMalvado = new FileWriter("src/textos/" + fileName + auxNumero + dotSomething);
						} catch (Exception e) {
							System.out.println("ERROR");
						}
 						
						System.out.println(GenerateNewSaveArch("MalvadoC1-V",".txt")); 
						System.out.println(GenerateNewSaveArch("MalvadoC2-V",".txt")); 
						break;
					
					case 4:
						System.out.println("[O] Retornando...");
						
					default:
						//throw new IllegalArgumentException("Unexpected value: " + lineScan);
						System.out.println("[!] Argumento incorrecto, regresando al menu estandar.");
					}
					break;
					*/
				default:
					throw new IllegalArgumentException("Valor inesperado: " + lineScan);
				}
		} while (lineScan != 7);
		}catch (Exception InputMismatchException) 
		{
			System.out.println("[!] Error de formato, se procederá a generar un reporte del último estado del grupo guardado.");
			String newRutaC1 = GenerateNewSaveArch("IME-ReporteC1-V",".txt");
			String newRutaC2 = GenerateNewSaveArch("IME-ReporteC2-V",".txt");
			System.out.println("Reportes guardados como: [" + newRutaC1 + " ; " + newRutaC2 + "]");
			try {
				FileWriter writerNewSaveC1 = new FileWriter("src/reportes/" + newRutaC1);
				FileWriter writerNewSaveC2 = new FileWriter("src/reportes/" + newRutaC2);
				for (int i = 0; i < 100; i++) 
				{	
					if (inWhasa[3][i] == null || inWhasa[3][i].equalsIgnoreCase("ELIMINADO"))
					{
						continue;
					}
					else if (inWhasa[3][i].equalsIgnoreCase("C1")) 
					{
						String soporteMoral = inWhasa[0][i] + ";" + inWhasa[1][i] + ";" + inWhasa[2][i] + ";" + inWhasa[3][i] + "\n";
						writerNewSaveC1.write(soporteMoral);
					}
					else if (inWhasa[3][i].equalsIgnoreCase("C2")) 
					{
						String soporteMoral = inWhasa[0][i] + ";" + inWhasa[1][i] + ";" + inWhasa[2][i] + ";" + inWhasa[3][i] + "\n";
						writerNewSaveC2.write(soporteMoral);
					}
				}
				writerNewSaveC1.close();
				writerNewSaveC2.close();
			} 
			catch (Exception e) {
				System.out.println("[!] ERROR DE ESCRITURA.");
			}
		}
		System.out.println("Antes de terminar el proceso ¿Deseas guardar los cambios hechos al listado de Alumnos inscritos sobreescribiendo Alumnos.txt?");
		System.out.println("1) Si");
		System.out.println("2) No");
		try 
		{
			int scanSave = scan.nextInt();
			switch(scanSave)
			{
			case 1:
				try 
				{
					FileWriter writerAlumnos = new FileWriter("src/textos/Alumnos.txt");
					for (int i = 0; i < 100; i++) 
					{
						if (archAlumnos[0][i] == null || archAlumnos[0][i].equalsIgnoreCase("ELIMINADO")) 
						{
							continue;
						} else 
						{
							String soporteMoral = archAlumnos[0][i] + ";" + archAlumnos[1][i] + ";" + archAlumnos[2][i] + ";" + archAlumnos[3][i] + "\n";
							writerAlumnos.write(soporteMoral);
						}       
					}
					writerAlumnos.close();
				} catch (Exception e) 
				{
					System.out.println("[!] Ruta no encontrada");
				}
				System.out.println("Bueno, se procederá a salir sobrescribiendo el archivo de Alumnos.txt, gracias por su tiempo.");
				break;
			default:
				System.out.println("Bueno, se procederá a salir sin guardad, gracias por su tiempo.");
				break;
			}		
		}catch (Exception InputMismatchException) 
		{
			System.out.println("Bueno, se procederá a salir sin guardad, gracias por su tiempo...");
		}
		scan.close();
	}
}
