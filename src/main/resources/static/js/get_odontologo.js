window.addEventListener('load', function () {

  (function () {
    const url = '/odontologos/todos';
    const settings = {
      method: 'GET'
    }
    fetch(url, settings)

      .then(function (response) {
        return response.json();
      })

      .then(function (data) {

        for (odontologo of data) {


          console.log("nombre:" + odontologo.nombre);

          var table = document.querySelector("#odontologoTable");
          var odontologoRow = table.insertRow();

          let tr_id = 'tr_' + odontologo.id;
          odontologoRow.id = tr_id;

          let updateButton = '<button' +
            ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
            ' type="button" onclick="findBy(' + odontologo.id + ')" class="btn btn-info btn_id">' +
            odontologo.id +
            '</button>';

          let deleteButton = '<button' +
            ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
            ' type="button" onclick="deleteBy(' + odontologo.id + ')" class="btn btn-danger btn_delete">' +
            '&times' +
            '</button>';

          odontologoRow.innerHTML = '<td>' + updateButton + '</td>' +
            '<td class=\"td_nombre\">' + odontologo.nombre + '</td>' +
            '<td class=\"td_apellido\">' + odontologo.apellido + '</td>' +
            '<td class=\"td_matricula\">' + odontologo.matricula + '</td>' +
            '<td>' + deleteButton + '</td>';

        };
      })
  })

    (function () {
      let pathname = window.location.pathname;
      if (pathname == "/listaOdontologo.html") {
        document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


})