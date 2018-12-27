/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Utilizador
 */
public class ConfiguraFacil{
    
    private Configuracoes configuracoes;
    private Pacotes pacotes;
    private Componentes componentes;
    private Configuracao configuracao;
    
    public ConfiguraFacil() {
        this.configuracoes = new Configuracoes();
        this.pacotes = new Pacotes();
        this.componentes = new Componentes();
        this.configuracao = new Configuracao();
    }
    
    public void adicionaComponenteObrigatorio(int id){
        Obrigatorio obrigatorio;
        obrigatorio = this.componentes.getObrigatorio(id);
        this.configuracao.adicionaComponenteObrigatorio(id, obrigatorio.getPreco());
    }
    
    public List<Opcional> alteracoesComponenteOpcionalNecessarios(int id){
        Opcional comp = this.componentes.getOpcional(id);
        List<Opcional> necessarios = new ArrayList<>();
        
        for(int i : comp.getListaNecessarios()){
            if(!(this.configuracao.containsOpcional(i))){
                Opcional nec = this.componentes.getOpcional(i);
                necessarios.add(nec);
            }
        }
        
        return necessarios;
    }
    
    public List<Opcional> alteracoesComponenteOpcionalIncompativeis(int id){
        Opcional comp = this.componentes.getOpcional(id);
        List<Opcional> incompativeis = new ArrayList<>();
        
        for(int i : comp.getListaIncompativeis()){
            if(this.configuracao.containsOpcional(i)){
                Opcional inc = this.componentes.getOpcional(i);
                incompativeis.add(inc);
            }
        }
        
        return incompativeis;
    }
    
    public List<Pacote> alteracoesComponenteOpcionalPacotesIncompativeis(int id){
        List<Pacote> pacotesIncompativeis = new ArrayList<>();
        
        for(int i : this.configuracao.getPacotes()){
            Pacote p = this.pacotes.getPacote(i);
            if(p.componenteIncompativel(id)){
                pacotesIncompativeis.add(p);
            }
        }
        
        return pacotesIncompativeis;
    }
    
    // percorrer lista dos pacotes da configuracao, para cada pacote tenho que verificar se o
    // componente que eu quero adicionar esta la (na lista dos incompativeis)
    public void adicionaComponenteOpcional(int id){
        Opcional comp = this.componentes.getOpcional(id);
        float preco;
        
        for(int i : comp.getListaNecessarios()){
            if(!(this.configuracao.containsOpcional(i))){
                preco = this.componentes.getOpcional(i).getPreco();
                this.configuracao.adicionaComponenteOpcional(i, preco);
            }
        }
        
        for(int i : comp.getListaIncompativeis()){
            if(this.configuracao.containsOpcional(i)){
                preco = this.componentes.getOpcional(i).getPreco();
                this.configuracao.removeComponenteOpcional(i, preco);
            }
        }
        
        List<Pacote> pacotesIncompativeis = alteracoesComponenteOpcionalPacotesIncompativeis(id);
        for(Pacote p : pacotesIncompativeis){
            this.configuracao.removePacote(p.getId(), p.getPreco());
        }
        
        this.configuracao.adicionaComponenteOpcional(id, comp.getPreco());
    }
    
    public List<Integer> alteracoesPacoteCompNecessarios(int id){
        Pacote p = this.pacotes.getPacote(id);
        List<Integer> nec = p.getListaComponentesNecessarios();
        List<Integer> res = new ArrayList<>();
        
        for(int i : nec){
            if(!(this.configuracao.containsOpcional(i))){
                res.add(i);
            }
        }
        
        return res;
    }
        
    public List<Integer> alteracoesPacoteCompIncompativeis(int id){
        Pacote p = this.pacotes.getPacote(id);
        List<Integer> inc = p.getListaComponentesIncompativeis();
        List<Integer> res = new ArrayList<>();

        for(int i : inc){
            if(this.configuracao.containsOpcional(i)){
                res.add(i);
            }
        }
        
        return res;
    }
    
    public List<Integer> alteracoesPacotePacotesIncompativeis(int id){
        Pacote p = this.pacotes.getPacote(id);
        List<Integer> pInc = p.getListaPacotesIncompativeis();
        List<Integer> res = new ArrayList<>();

        for(int i : pInc){
            if(this.configuracao.containsPacote(i)){
                res.add(i);
            }
        }
        
        return res;
    }
    
    public void adicionaPacote(int id){
        Pacote pacote = this.pacotes.getPacote(id);
        float preco;
        
        for(int i : pacote.getListaComponentesNecessarios()){
            if(!(this.configuracao.containsOpcional(i))){
                preco = this.componentes.getOpcional(i).getPreco();
                this.configuracao.adicionaComponenteOpcional(i, preco);
            }
        }
        
        for(int i : pacote.getListaComponentesIncompativeis()){
            if(this.configuracao.containsOpcional(i)){
                preco = this.componentes.getOpcional(i).getPreco();
                this.configuracao.removeComponenteOpcional(i, preco);
            }
        }
            
        for(int i : pacote.getListaPacotesIncompativeis()){
            if(this.configuracao.containsPacote(i)){
                preco = this.pacotes.getPacote(i).getPreco();
                this.configuracao.removePacote(i, preco);
            }
        }
        
        this.configuracao.adicionaPacote(id, pacote.getPreco());
    }
    
    public void adicionarStock(boolean obrigatorio, int id, int qtd){
        if(obrigatorio){    
            this.componentes.getObrigatorio(id).addStock(qtd);
        }
        else{
            this.componentes.getOpcional(id).addStock(qtd);
        }
    }
    
    public boolean temStockPacote(int id){
        Pacote p = this.pacotes.getPacote(id);
        boolean res = false;
        
        for(int i : p.getListaComponentesPacote()){
                res = this.componentes.temStock(i, false);
                if(!res){
                    return res;
                }
        }
        
        return res;
    }
    
    public Configuracao obterProximaConfiguracao(){
        List<Configuracao> configs = this.configuracoes.getConfiguracoes();
        boolean bool = false;
        
        for(Configuracao c : configs){
            for(int i : c.getComponentesObrigatorios()){
                bool = this.componentes.temStock(i, true);
                if(!bool){
                    break;
                }
            }
            
            if(bool){
                for(int i : c.getComponentesOpcionais()){
                    bool = this.componentes.temStock(i, false);
                    if(!bool){
                        break;
                    }
                }
            }
            
            if(bool){
                for(int p : c.getPacotes()){
                    bool = temStockPacote(p);
                    if(!bool){
                        break;
                    }
                }
            }
            
            if(bool){
                for(int i : c.getComponentesObrigatorios()){
                    this.componentes.reduzStockObrigatorio(i);
                }
                for(int i : c.getComponentesOpcionais()){
                    this.componentes.reduzStockOpcional(i);
                }
                for(int i : c.getPacotes()){
                    for(int j : this.pacotes.getPacote(i).getListaComponentesPacote()){
                        this.componentes.reduzStockOpcional(j);
                    }
                }
                return c;
            }
        }
        
        return null;        
    }
    
    public List<Componente> getListaComponentes(int id){
        List<Componente> res = new ArrayList<>();
        Configuracao c = this.configuracoes.getConfiguracao(id);
        
        for(int i : c.getComponentesObrigatorios()){
            res.add(this.componentes.getObrigatorio(i));
        }
        for(int i : c.getComponentesOpcionais()){
            res.add(this.componentes.getOpcional(i));
        }
        for(int i : c.getPacotes()){
            for(int j : this.pacotes.getPacote(i).getListaComponentesPacote()){
                res.add(this.componentes.getOpcional(j));
            }
        }
        
        return res;
    }

    public float escolhePacotesOtimos(float orcamento){
        List<Pacote> ordenadoPreco = new ArrayList<>();
        float precoMaisBarato = this.pacotes.getPrecoPacoteMaisBarato();
        
        for(Pacote p : this.pacotes.getPacotes().values()){
            ordenadoPreco.add(p);
        }
        ordenadoPreco.sort(new PacotePrecoComparator());
        
        List<Integer> listaComponentesNecessariosParaPacote;
        for(Pacote p : ordenadoPreco){
            if(orcamento < precoMaisBarato){
                break;
            }
            
            if((p.getPreco() <= orcamento) && (!(ocorremIncompatibilidadesPacote(p)))){
                listaComponentesNecessariosParaPacote = alteracoesPacoteCompNecessarios(p.getId());
                float valor = p.getPreco();
                for(int i : listaComponentesNecessariosParaPacote){
                    valor += this.componentes.getOpcional(i).getPreco();
                }
                if((valor <= orcamento)){
                    this.configuracao.adicionaPacote(p.getId(), p.getPreco());
                    for(int i : listaComponentesNecessariosParaPacote){
                        this.configuracao.adicionaComponenteOpcional(this.componentes.getOpcional(i).getId(), this.componentes.getOpcional(i).getPreco());
                    }
                    orcamento -= valor;
                }
            }
        }
        
        return orcamento;
    }
    
    public float escolheComponentesOtimos(float orcamento){
        List<Opcional> ordenadoPreco = new ArrayList<>();
        float precoMaisBarato = this.componentes.getPrecoComponenteOpcionalMaisBarato();
        
        for(Opcional o : this.componentes.getComponentesOpcionais().values()){
            ordenadoPreco.add(o);
        }
        ordenadoPreco.sort(new ComponentePrecoComparator());
        
        List<Opcional> listaComponentesNecessarios;
        for(Opcional o : ordenadoPreco){
            if(orcamento < precoMaisBarato){
                break;
            }
            
            if((o.getPreco() <= orcamento) && (!(ocorremIncompatibilidadesComponentes(o)))){
                listaComponentesNecessarios = alteracoesComponenteOpcionalNecessarios(o.getId());
                float valor = o.getPreco();
                for(Opcional n : listaComponentesNecessarios){
                    valor += n.getPreco();
                }
                if((valor <= orcamento)){
                    this.configuracao.adicionaComponenteOpcional(o.getId(), o.getPreco());
                    for(Opcional n : listaComponentesNecessarios){
                        this.configuracao.adicionaComponenteOpcional(n.getId(), n.getPreco());
                    }
                    orcamento -= valor;
                }
            }
        }
        
        return orcamento;
    }
    
    public Configuracao configuracaoOptima(float orcamento){
        escolhePacotesOtimos(orcamento);
        escolheComponentesOtimos(orcamento);
        
        return this.configuracao;
    }
    
    public boolean ocorremIncompatibilidadesPacote(Pacote p){
        for(int i : p.getListaComponentesIncompativeis()){
            if(this.configuracao.getComponentesOpcionais().contains(i)){
                return true;
            }
        }
        for(int i : p.getListaPacotesIncompativeis()){
            if(this.configuracao.getPacotes().contains(i)){
                return true;
            }
        }
        return false;
    }
    
    public boolean ocorremIncompatibilidadesComponentes(Opcional o){
        for(int i : o.getListaIncompativeis()){
            if(this.configuracao.getComponentesOpcionais().contains(i)){
                return true;
            }
        }
        for(int i : o.getListaIncompativeis()){
            if(this.configuracao.getPacotes().contains(i)){
                return true;
            }
        }
        return false;
    }
    
    public void escolheModelo(String modelo){
        this.configuracao.alteraModelo(modelo);
    }
}
