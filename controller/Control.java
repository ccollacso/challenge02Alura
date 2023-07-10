package controller;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import entity.Multiplicador;
import view.Principal;

public class Control {

	Multiplicador multiplicador = new Multiplicador();

	public void ejectutar(Principal principal) {

		String opcionInicial = (String) principal.getJcmbDesde().getSelectedItem();
		String opcionFinal = (String) principal.getJcmbHacia().getSelectedItem();
		float numeroInicial = Float.valueOf( principal.getJtxfCantidadInicial().getText() );
		float multiplicador = 0;
		float montoFinal = 0;

		int conversorElegido;
		
		if( principal.getJrbtnDivisas().isSelected() ) {
			conversorElegido = 0;
		} else if ( principal.getJrbtnTemperaturas().isSelected() ) {
			conversorElegido = 1;
		} else if ( principal.getJrbtnDistancias().isSelected() ) {
			conversorElegido = 2;
		} else { 
			conversorElegido = 3;
		}

		if (conversorElegido == 0 || conversorElegido == 2) {
			multiplicador = retornarMultiplicador(opcionInicial, opcionFinal, conversorElegido);
            montoFinal = numeroInicial * multiplicador;
        } else if (conversorElegido == 1) {
        	montoFinal = convertirTemperaturas(opcionInicial, opcionFinal, numeroInicial);
        } else {
        	System.out.println("Error de conversor elegido");
        }
		
		principal.getJtxfCantidadFinal().setText( Float.toString(montoFinal) );
	}

	public float retornarMultiplicador(String opcionInicial, String opcionFinal, int tabla) {

		List<Multiplicador> lsMultiplicadores = obtenerTablaDeMultiplicadores(tabla);

		for (int i = 0; i < lsMultiplicadores.size(); i++) {
			if (lsMultiplicadores.get(i).getOpcion1().equals(opcionInicial)
					&& lsMultiplicadores.get(i).getOpcion2().equals(opcionFinal)) {
				return lsMultiplicadores.get(i).getMultiplicador();
			}
			if (lsMultiplicadores.get(i).getOpcion2().equals(opcionInicial)
					&& lsMultiplicadores.get(i).getOpcion1().equals(opcionFinal)) {
				return lsMultiplicadores.get(i).getMultiplicadorInverso();
			}
		}
		return 0;
	}

	public List<Multiplicador> obtenerTablaDeMultiplicadores(int tabla) {

		if (tabla == 0) {
			Multiplicador tasaSolesDolares = new Multiplicador("soles", "dólares", 0.28f, 3.5f);
			Multiplicador tasaSolesEuros = new Multiplicador("soles", "euros", 0.25f, 4.07f);
			Multiplicador tasaDolaresEuros = new Multiplicador("dólares", "euros", 0.93f, 1.08f);

			List<Multiplicador> lsMultiplicadores = Arrays.asList(tasaSolesDolares, tasaSolesEuros, tasaDolaresEuros);

			return lsMultiplicadores;
		}

		if (tabla == 2) {
			Multiplicador conversorMetrosCentimetros = new Multiplicador("metros", "centímetros", 100, 0.01f);
			Multiplicador conversorMetrosKilometros = new Multiplicador("metros", "kilómetros", 0.001f, 1000);
			Multiplicador conversorCentimetrosKilometros = new Multiplicador("centímetros", "kilómetros", 0.00001f,
					100000);

			List<Multiplicador> lsMultiplicadores = Arrays.asList(conversorMetrosCentimetros, conversorMetrosKilometros,
					conversorCentimetrosKilometros);

			return lsMultiplicadores;
		}
		return null;
	}

	public float convertirTemperaturas(String opcionInicial, String opcionFinal, float numeroInicial) {

		float montoFinal = 0;

		if (opcionInicial.equals("celsius") && opcionFinal.equals("kelvin")) {
			Function<Float, Float> convertir = a -> a + 273;
			montoFinal = convertir.apply(numeroInicial);
		}
		if (opcionInicial.equals("kelvin") && opcionFinal.equals("celsius")) {
			Function<Float, Float> convertir = a -> a - 273;
			montoFinal = convertir.apply(numeroInicial);
		}
		if (opcionInicial.equals("celsius") && opcionFinal.equals("fahrenheit")) {
			Function<Float, Float> convertir = a -> (a * 1.8f) + 32;
			montoFinal = convertir.apply(numeroInicial);
		}
		if (opcionInicial.equals("fahrenheit") && opcionFinal.equals("celsius")) {
			Function<Float, Float> convertir = a -> (a - 32) / 1.8f;
			montoFinal = convertir.apply(numeroInicial);
		}
		if (opcionInicial.equals("fahrenheit") && opcionFinal.equals("kelvin")) {
			Function<Float, Float> deFarhenheitHaciaCelsius = a -> (a - 32) / 1.8f;
			float auxiliar = deFarhenheitHaciaCelsius.apply(numeroInicial);
			Function<Float, Float> deCelsiusHaciaKelvin = a -> a + 273;
			montoFinal = deCelsiusHaciaKelvin.apply(auxiliar);
		}
		if (opcionInicial.equals("kelvin") && opcionFinal.equals("fahrenheit")) {
			Function<Float, Float> deKelvinHaciaCelsius = a -> a - 273;
			float auxiliar = deKelvinHaciaCelsius.apply(numeroInicial);
			Function<Float, Float> deCelsiusHaciaFarhenheit = a -> (a * 1.8f) + 32;
			montoFinal = deCelsiusHaciaFarhenheit.apply(auxiliar);
		}
		return montoFinal;
	}

	public void inicializarDivisas(Principal principal) {

		JComboBox<String> jcmbDesde = principal.getJcmbDesde();
		jcmbDesde.removeAllItems();
		jcmbDesde.addItem("soles");
		jcmbDesde.addItem("dólares");
		jcmbDesde.addItem("euros");
		principal.setJcmbDesde(jcmbDesde);

		JComboBox<String> jcmbHacia = principal.getJcmbHacia();
		jcmbHacia.removeAllItems();
		jcmbHacia.addItem("dólares");
		jcmbHacia.addItem("euros");
		jcmbHacia.addItem("soles");
		principal.setJcmbHacia(jcmbHacia);
	}

	public void inicializarTemperaturas(Principal principal) {

		JComboBox<String> jcmbDesde = principal.getJcmbDesde();
		jcmbDesde.removeAllItems();
		jcmbDesde.addItem("celsius");
		jcmbDesde.addItem("kelvin");
		jcmbDesde.addItem("fahrenheit");
		principal.setJcmbDesde(jcmbDesde);

		JComboBox<String> jcmbHacia = principal.getJcmbHacia();
		jcmbHacia.removeAllItems();
		jcmbHacia.addItem("kelvin");
		jcmbHacia.addItem("fahrenheit");
		jcmbHacia.addItem("celsius");
		principal.setJcmbHacia(jcmbHacia);
	}

	public void inicializarDistancias(Principal principal) {

		JComboBox<String> jcmbDesde = principal.getJcmbDesde();
		jcmbDesde.removeAllItems();
		jcmbDesde.addItem("metros");
		jcmbDesde.addItem("kilómetros");
		jcmbDesde.addItem("centímetros");
		principal.setJcmbDesde(jcmbDesde);

		JComboBox<String> jcmbHacia = principal.getJcmbHacia();
		jcmbHacia.removeAllItems();
		jcmbHacia.addItem("kilómetros");
		jcmbHacia.addItem("centímetros");
		jcmbHacia.addItem("metros");
		principal.setJcmbHacia(jcmbHacia);
	}

	public boolean validar(Principal principal) {
		
		if(principal.getJcmbDesde().getSelectedItem() == null) {
			 JOptionPane.showMessageDialog(null, "Se debe seleccionar una opción de conversión");
			 return false;
		}
		if( principal.getJcmbDesde().getSelectedItem().toString().
				equals( principal.getJcmbHacia().getSelectedItem().toString() ) ) {
			 JOptionPane.showMessageDialog(null, "Las opciones elegidas no pueden ser iguales");
			 return false;
		}
		if(principal.getJtxfCantidadInicial().getText().equals("")) {
			 JOptionPane.showMessageDialog(null, "La cantidad ingresada no puede ser vacía");
			return false;
		}
		return true;
	}

	public void limpiar(Principal principal) {
		
		principal.getJcmbDesde().removeAllItems();
		principal.getJcmbHacia().removeAllItems();
		principal.getJtxfCantidadInicial().setText("");
		principal.getJtxfCantidadFinal().setText("");
		principal.getJbtngrpConversores().clearSelection();
	}

	public void cerrarVentana(Principal principal) {
		
		principal.dispose();
		System.exit(0);
	}
}
