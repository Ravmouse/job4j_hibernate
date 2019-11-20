/**
 * Здесь добавляется только одна задача в построенную таблицу, поэтому checkbox не checked (т.к. задача является
 * невыполненной) и не имеет id. Id будут создаваться при вызове get().
 */
function add() {
    $.ajax({
        type: "POST",
        url: './add',
        data: $('#in').val(),
        dataType: 'json',
        success: function (json) {
            $('table tr:last').after('<tr><td>' + json.id + '</td><td>' + json.description + '</td><td>'
                + json.created + '</td><td>' + '<input type="checkbox">' + '</td></tr>');
        }
    });
    document.getElementById('in').value = '';
}

/**
 * Вызов ф-ии get() для получения всех задач.
 * 2 сек. ставлю для того, чтобы Hibernate успел стартовать.
 */
window.setTimeout(function () {
    get('./loadAll');
}, 2000);

/**
 * После получения ответа от ajax-запроса каждое значение json добавляется вместе со строкой в таблицу.
 * В последнем столбце добавляется checkbox.
 * Проверяется, если true, то добавленный checkbox делается checked.
 */
function get(url) {
    $.ajax({
        url: url,
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            $.each(data, function (k, v) {
                $('table tr:last').after('<tr class="myRow"><td>' + v.id + '</td><td>' + v.description + '</td><td>'
                    + v.created + '</td><td>' + '<input type="checkbox" id="ch' + k + '">' + '</td></tr>');

                if (v.done) {
                    $('#ch' + k).prop("checked", true);
                }
            });
        }
    })
}

/**
 * Удаляет Label.
 * Очищает всю таблицу, кроме заголовка.
 * Если галочка стоит, снимает галочку, добавляет Label "НЕВЫПОЛНЕННЫЕ" и загружает только невыпол.задачи.
 * Если галочки нет, ставит галочку, добавляет Label "ВСЕ" и загружает ВСЕ задачи.
 */
function change() {
    let elem = $('#cb');
    $('label').remove();

    $('.myRow').remove();

    if (elem.prop('checked')) {
        elem.after('<label>ВСЕ задачи</label>');
        get('./loadAll');
    } else {
        elem.after('<label>НЕВЫПОЛНЕННЫЕ задачи</label>');
        get('./loadNot');
    }
}