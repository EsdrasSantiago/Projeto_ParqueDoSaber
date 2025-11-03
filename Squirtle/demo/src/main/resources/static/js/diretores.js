$(document).ready(function () {
  $.get("/api/diretores", function (diretores) {
    let tabela = $("#tabela-diretores");
    tabela.empty();

    diretores.forEach(function (diretor) {
      tabela.append(`
        <tr>
          <td>${diretor.id}</td>
          <td>${diretor.nome}</td>
          <td>${diretor.email}</td>
          <td>${diretor.telefone}</td>
          <td>
            <form action="/diretores/deletar/${diretor.id}" method="post" onsubmit="return confirm('Tem certeza que deseja excluir este diretor?')" style="display:inline;">
              <button type="submit" class="btn btn-sm btn-danger">Excluir</button>
            </form>
            <a href="/diretores/editar/${diretor.id}" class="btn btn-sm btn-warning">Editar</a>
          </td>
        </tr>
      `);
    });
  });
});