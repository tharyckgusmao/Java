package dados;

import java.io.Serializable;
import java.util.GregorianCalendar;

import utilitarios.LtpUtil;

/**
 * Classe para criacao de objetos do tipo Produto onde implementa a clase
 * Serializable com valores primitivos e obejtos , String nome; double
 * precoUnitatio e variaveis do tipo gregorianCalendar;
 *
 * @author Tharyck Gusmao Metzker
 * @param
 * @return
 */
public class Produto implements Serializable {

	private static int sequence = 1;

	private int codigo;
	private String nome;
	private double precoUnitatio;
	private GregorianCalendar dataInclusao = new GregorianCalendar();
	private GregorianCalendar dataUltAlteracao = new GregorianCalendar();

	/**
	 * Metodo Construtor com passagem de parametro para a criacao de objeto.
	 *
	 * @author Tharyck Gusmao Metzker.
	 * @param String
	 *            nome,double precoUnitatio, GregorianCalendar dataInclusao,
	 *            GregorianCalendar dataUltAlteracao;
	 * @return nao ha retono para o metodo construtor.
	 */
	public Produto(String nome, double precoUnitatio,
			GregorianCalendar dataInclusao, GregorianCalendar dataUltAlteracao) {

		codigo = sequence++;
		this.nome = nome;
		this.precoUnitatio = precoUnitatio;
		this.dataInclusao = dataInclusao;
		this.dataUltAlteracao = dataUltAlteracao;
	}

	/**
	 * Metodo getNome para captura do valor da variavel String no objeto.
	 *
	 * @author Tharyck Gusmao Metzker.
	 * @param Nao
	 *            a passagem de paramentro.
	 * @return Retorno String nome;
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Metodo setNome para setagem do valor da variavel String no objeto.
	 *
	 * @author Tharyck Gusmao Metzker.
	 * @param String
	 *            .
	 * @return metodo void sem retorno;
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Metodo getPrecoUnitatio para captura do valor da variavel double no
	 * objeto.
	 *
	 * @author Tharyck Gusmao Metzker.
	 * @param Nao
	 *            a passagem de paramentro.
	 * @return Retorno double;
	 */
	public double getPrecoUnitatio() {
		return precoUnitatio;
	}

	/**
	 * Metodo setNome para setagem do valor da variavel double no objeto.
	 *
	 * @author Tharyck Gusmao Metzker.
	 * @param double .
	 * @return metodo void sem retorno;
	 */
	public void setPrecoUnitatio(double precoUnitatio) {
		this.precoUnitatio = precoUnitatio;
	}

	/**
	 * Metodo getDataInclusao para captura do valor da variavel
	 * gregorianCalendar no objeto.
	 *
	 * @author Tharyck Gusmao Metzker.
	 * @param Nao
	 *            a passagem de paramentro.
	 * @return Retorno gregorianCalendar.
	 */

	public GregorianCalendar getDataInclusao() {
		return dataInclusao;
	}

	/**
	 * Metodo setDataInclusao para setagem do valor da variavel ddo tipo
	 * GregorianCalendar no objeto.
	 *
	 * @author Tharyck Gusmao Metzker.
	 * @param gregorianCalendar
	 *            .
	 * @return metodo void sem retorno;
	 */
	public void setDataInclusao(GregorianCalendar dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	/**
	 * Metodo dataInclusao para captura do valor da variavel gregorianCalendar
	 * no objeto.
	 *
	 * @author Tharyck Gusmao Metzker.
	 * @param Nao
	 *            a passagem de paramentro.
	 * @return Retorno gregorianCalendar dataUltAlteracao.
	 */

	public GregorianCalendar getDataUltAlteracao() {
		return dataUltAlteracao;
	}

	/**
	 * Metodo setDataUltAlteracao para setagem do valor da variavel
	 * gregorianCalendar no objeto.
	 *
	 * @author Tharyck Gusmao Metzker.
	 * @param Nao
	 *            a passagem de paramentro.
	 * @return metodo void sem retorno;
	 */
	public void setDataUltAlteracao(GregorianCalendar dataUltAlteracao) {
		this.dataUltAlteracao = dataUltAlteracao;
	}

	/**
	 * Metodo setSequence para setagem do valor da variavel static int no
	 * objeto.
	 *
	 * @author Tharyck Gusmao Metzker.
	 * @param int.
	 * @return metodo void sem retorno;
	 */
	public static void setSequence(int sequence) {
		Produto.sequence = sequence;
	}

	public int getCodigo() {
		return codigo;
	}

	/**
	 * Metodo toString Override do metodo principal para impressao dos dados
	 * alocados nas variaveis do objeto todos formatados.
	 *
	 * @author Tharyck Gusmao Metzker.
	 * @param
	 * @return metodo void sem retorno;
	 */
	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + " \n nome=" + nome
				+ ",\n precoUnitatio=" + precoUnitatio + ",\n dataInclusao="
				+ LtpUtil.formatarData(dataInclusao, "dd/MM/yyyy hh:mm")
				+ "\n  Data de altera��o = "
				+ LtpUtil.formatarData(dataUltAlteracao, "dd/MM/yyyy hh:mm");
	}

}
