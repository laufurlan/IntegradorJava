window.addEventListener('load', function () {
    window.deleteBy = (id) => {
        if (window.confirm('Desea eliminar este Paciente?')) {
            const url = '/paciente/'+ id;
            const settings = {
                method: 'DELETE',
            };

            fetch(url, settings).then((response) => {
                alert('Paciente eliminado correctamente');
                location.reload();
            });
        }
    };
})