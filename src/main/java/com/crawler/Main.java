package com.crawler;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.crawler.exceptions.DocumentNotFoundException;
import com.crawler.exceptions.ElementNotFoundException;
import com.crawler.services.CurrencyService;
// import com.crawler.services.CurrencyService;
import com.crawler.services.WeatherService;

public class Main {
        public static void main(String[] args) {
                try {
                        var option = Integer.valueOf(
                                        JOptionPane.showInputDialog("Digite 1 para Cotações ou 2 para Clima: "));

                        if (option == 1) {
                                CurrencyService currencyService = new CurrencyService();
                                JOptionPane.showMessageDialog(null,
                                                "USD para BRL = " + currencyService.getDollarToBRL());
                        } else if (option == 2) {
                                WeatherService weatherService = new WeatherService(
                                                JOptionPane.showInputDialog("Informe a cidade: "));

                                JOptionPane.showMessageDialog(null,
                                                weatherService.getTitle() + "\nMIN: "
                                                                + weatherService.getMinTemperatureText()
                                                                + "\nMAX: " + weatherService.getMaxTemperatureText());
                        } else {
                                JOptionPane.showMessageDialog(null, "Opção inválida!");
                        }

                } catch (DocumentNotFoundException docException) {
                        System.out.println("Documento não pode ser encontrado. ");
                        System.out.println("Erro: " + docException.getMessage());
                } catch (ElementNotFoundException elementException) {
                        System.out.println("Elemento não pode ser encontrado. ");
                        System.out.println("Erro: " + elementException.getMessage());
                } catch (IOException exception) {
                        System.out.println("Erro: " + exception.getMessage());
                }
        }
}