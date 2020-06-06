package gradle.cucumber

import ar.unq.unqtrading.entities.OrdenDeVenta
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.Assert
import org.junit.runner.RunWith
import java.time.LocalDate

@RunWith(Cucumber::class)
@CucumberOptions(features = ["src/test/resources"])
class VisualizarOrdenesDeVentaSteps : SpringIntegrationTest(){
    lateinit var orden1: OrdenDeVenta
    lateinit var orden2: OrdenDeVenta
    lateinit var ordenes: List<OrdenDeVenta>
    lateinit var response: OrdenDeVenta

    @Given("una empresa con nombre {string} y {int} ordenes de venta")
    fun una_empresa_con_nombre_y_ordenes_de_venta(nombre: String, cant: Int) {
        orden1 = OrdenDeVenta()
        orden2 = OrdenDeVenta()
        orden1.nombreEmpresa = nombre
        orden2.nombreEmpresa = nombre
    }

    @When("pregunto cuales son sus ordenes de venta")
    fun pregunto_cuales_son_sus_ordenes_de_venta() {
        ordenes = listOf(orden1, orden2)
    }

    @Then("debo visualizar {int} ordenes de venta")
    fun debo_visualizar_ordenes_de_venta(cantidad: Int?) {
        Assert.assertEquals(cantidad, ordenes.size)
    }

    @Given("una empresa con nombre {string}")
    fun crearOrdenDeVenta(nombre: String) {
        orden1 = OrdenDeVenta()
        orden1.nombreEmpresa = nombre
        orden1.cantidadDeAcciones = 1000
        orden1.precio = 500
        orden1.fechaDeCreacion = LocalDate.now()
        orden1.fechaDeVencimiento = LocalDate.of(2020, 7, 25)
    }

    @When("creo una orden de venta")
    fun postOrdenDeVenta() {
        val url = "$DEFAULT_URL/save"
        response = restTemplate.postForObject(url, orden1, OrdenDeVenta::class.java)!!
    }

    @Then("la orden de venta tiene un id")
    fun assertId() {
        Assert.assertEquals(1, response.id)
    }
}