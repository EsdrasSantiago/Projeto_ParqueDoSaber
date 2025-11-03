$(document).ready(function () {
  $.get("/api/alunos", function (alunos) {
    let tabela = $("#tabela-alunos");
    tabela.empty();

    alunos.forEach(function (aluno) {
      tabela.append(`
        <tr>
          <td>${aluno.id}</td>
          <td>${aluno.nome}</td>
          <td>${aluno.email}</td>
          <td>${aluno.telefone}</td>
          <td>
            <form action="/alunos/deletar/${aluno.id}" method="post" onsubmit="return confirm('Tem certeza que deseja excluir este aluno?')" style="display:inline;">
              <button type="submit" class="btn btn-sm btn-danger">Excluir</button>
            </form>
            <a href="/alunos/editar/${aluno.id}" class="btn btn-sm btn-warning">Editar</a>
          </td>
        </tr>
      `);
    });
  });
});