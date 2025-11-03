$(document).ready(function () {
  $.get("/api/turmas", function (turmas) {
    let tabela = $("#tabela-turmas");
    tabela.empty();

    turmas.forEach(function (turma) {
      tabela.append(`
        <tr>
          <td>${turma.id}</td>
          <td>${turma.nome}</td>
          <td>${turma.turno}</td>
          <td>${turma.sala}</td>
          <td>
            <form action="/turmas/deletar/${turma.id}" method="post" onsubmit="return confirm('Tem certeza que deseja excluir esta turma?')" style="display:inline;">
              <button type="submit" class="btn btn-sm btn-danger">Excluir</button>
            </form>
            <a href="/turmas/editar/${turma.id}" class="btn btn-sm btn-warning">Editar</a>
          </td>
        </tr>
      `);
    });
  });
});
