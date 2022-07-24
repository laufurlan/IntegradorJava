window.addEventListener('load', function () {
    window.deleteBy = (id) => {
      if (window.confirm('Desea eliminar este odontologo?')) {
        const url = '/odontologos/'+ id;
        const settings = {
          method: 'DELETE',
        };

        fetch(url, settings).then((response) => {
          alert('Odontologo eliminado correctamente');
          location.reload();
        });
      }
    };
})