let meuGrafico = null;

async function processarArquivo() {
    const fileInput = document.getElementById('csvFile');
    const file = fileInput.files[0];

    if (file) {
        try {
            const formData = new FormData();
            formData.append('file', file);

            const response = await fetch('http://localhost:8081/processar-csv', {
                method: 'POST',
                body: formData
            });

            if (!response.ok) {
                const erro = await response.json();
                throw new Error(erro.erro || 'Erro ao processar arquivo');
            }

            const dados = await response.json();
            const descricoes = dados.transacoes.map(t => t.descricao);
            const valores = dados.transacoes.map(t => t.valorTotal);

            criarGrafico(descricoes, valores);
        } catch (error) {
            alert('Erro ao processar arquivo: ' + error.message);
        }
    } else {
        alert('Por favor, selecione um arquivo CSV');
    }
}

function criarGrafico(descricoes, valores) {
    const ctx = document.getElementById('grafico').getContext('2d');
    
    // Destruir gráfico anterior se existir
    if (meuGrafico) {
        meuGrafico.destroy();
    }

    meuGrafico = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: descricoes,
            datasets: [{
                label: 'Valor Total (R$)',
                data: valores,
                backgroundColor: 'rgba(54, 162, 235, 0.6)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                title: {
                    display: true,
                    text: 'Gastos Agrupados por Descrição',
                    font: {
                        size: 16
                    }
                },
                legend: {
                    position: 'top'
                },
                tooltip: {
                    callbacks: {
                        label: function(context) {
                            let value = context.raw;
                            return 'R$ ' + value.toLocaleString('pt-BR', {
                                minimumFractionDigits: 2,
                                maximumFractionDigits: 2
                            });
                        }
                    }
                }
            },
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: {
                        callback: function(value) {
                            return 'R$ ' + value.toLocaleString('pt-BR', {
                                minimumFractionDigits: 2,
                                maximumFractionDigits: 2
                            });
                        }
                    }
                }
            }
        }
    });
} 