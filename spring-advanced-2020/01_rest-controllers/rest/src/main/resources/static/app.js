
const urlAuthors = 'http://localhost:8080/authors';

$('#loadAuthors').click(() => {

    $('.authors-container').empty();
    $('.books-container').empty();

    fetch(urlAuthors) // Fetch the data (GET request)
        .then((response) => response.json()) // Extract the JSON from the Response
        .then((json) => json.forEach((author, idx) => { // Render the JSON data to the HTML
            const tableRow = tableRowAuthor(author.id, author.name);
            $('.authors-container').append(tableRow);
        }));
});

$('body').on('click', 'button.book-btn', function () {
    let authorId = $(this).data('author-id');

    $('.books-container').empty();
    fetch(urlAuthors + '/' + authorId + '/books') // Fetch the data (GET request)
        .then((response) => response.json()) // Extract the JSON from the Response
        .then((json) => json.forEach((book, idx) => { // Render the JSON data to the HTML
            let tableRow =
                '<tr>' +
                '<td>' + book.title + '</td>' +
                '<td>' +
                '<button>Delete</button>' +
                '</td>' +
                '</tr>';

            $('.books-container').append(tableRow);
        }));
});

/**
 * @param id String ???
 * @param name String ???
 */
function tableRowAuthor(id, name) {
    const tableRow = createElement("tr");

    tableRow.appendChild(createCell(document.createTextNode(id)));
    tableRow.appendChild(createCell(document.createTextNode(name)));

    const button1 = createElement("button", "Show books");
    button1.className = "book-btn";
    button1.setAttribute("data-author-id", id);
    tableRow.appendChild(createCell(button1));

    const button2 = createElement("button", "Delete");
    button2.setAttribute("onclick", "deleteAuthor(" + id + ")");
    tableRow.appendChild(createCell(button2));

    return tableRow
}

function deleteAuthor(id) {
    const table = document.getElementsByClassName("authors-container")[0];
    const author = Array.from(table.children).find(row => row.firstChild.textContent == id);

    if (author) {
        const url = urlAuthors + '/' + id;

        const result =
            fetch(url, {method: 'delete'})
                // .then(response => response.json())
                // .then(data => {
                //     console.log('Success:', data);
                // })
                .catch((error) => {
                    console.error('Error:', error);
                });

        table.removeChild(author);
        clearChildren('books-container')
    }
}

function createElement(type, text) {
    let el = document.createElement(type);
    if (text !== null && text !== undefined) {
        el.innerText = text;
    }
    return el;
}

function createCell(element) {
    const td = document.createElement('td');
    td.appendChild(element);
    return td;
}

function clearChildren(className) {
    const parent = document.getElementsByClassName(className)[0];
    Array.from(parent.children).forEach(child => parent.removeChild(child))
}