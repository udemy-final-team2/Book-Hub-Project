function searchDocs() {
    const searchTerm = document.querySelector('#searchInput').value.toLowerCase();
    const docsTable = document.querySelector('#docsList');

    for (let i = 0; i < docsTable.rows.length; i++) {
        const title = docsTable.rows[i].cells[1].textContent.toLowerCase();
        const description = docsTable.rows[i].cells[2].textContent.toLowerCase();

        if (title.includes(searchTerm) || description.includes(searchTerm)) {
            docsTable.rows[i].style.display = '';
        } else {
            docsTable.rows[i].style.display = 'none';
        }
    }
}
function searchClear() {
    const searchInput = document.querySelector('#searchInput');
    const docsTable = document.querySelector('#docsList');
    searchInput.value = '';
    for (let i = 0; i < docsTable.rows.length; i++) {
        docsTable.rows[i].style.display = '';
    }
}

function editFolderTitle(button) {
    const parent = button.parentElement.parentElement;
    const title = parent.querySelector('.folder-title');
    const titleText = title.innerText;
    const input = document.createElement('input');
    input.value = titleText;
    input.classList.add('folder-title-input');
    parent.replaceChild(input, title);

    input.addEventListener('keypress', function (e) {
        if (e.key === 'Enter') {
            const newTitleText = input.value.trim();
            if (newTitleText !== '') {
                const newTitle = document.createElement('p');
                newTitle.classList.add('folder-title');
                newTitle.innerText = newTitleText;
                parent.replaceChild(newTitle, input);
            } else {
                const originalTitle = document.createElement('p');
                originalTitle.classList.add('folder-title');
                originalTitle.innerText = titleText;
                parent.replaceChild(originalTitle, input);
            }
        }
    });
    input.focus(); // Set focus on the input field
}

function openModal(content) {
    let modalContent = document.getElementById("modal-content");
    modalContent.innerText = content;
    document.getElementById("modal").style.display = "block";
}

function closeModal() {
    document.getElementById("modal").style.display = "none";
}

function openDeleteModal() {
    document.getElementById("delete-modal").style.display="block";
}
function closeDeleteModal() {
    document.getElementById("delete-modal").style.display="none";
}


function deleteSelectedDocuments() {
    const checkboxes = document.querySelectorAll('.document-checkbox');
    const selectedDocumentIds = [];
    checkboxes.forEach((checkbox) => {
        if (checkbox.checked) {
            selectedDocumentIds.push(checkbox.getAttribute('data-document-id'));
        }
    });
    if (selectedDocumentIds.length > 0) {
        const xhr = new XMLHttpRequest();
        xhr.open('POST', '/docs/delete');
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onload = function () {
            if (xhr.status === 200) {
                window.location.reload();
            } else {
                console.error(xhr.statusText);
            }
        };
        xhr.onerror = function () {
            console.error(xhr.statusText);
        };
        xhr.send(JSON.stringify({documentIds: selectedDocumentIds}));
    }


    checkboxes.forEach((checkbox) => {
        checkbox.addEventListener('change', () => {
            const selectedCheckboxCount = document.querySelectorAll('.document-checkbox:checked').length;
            console.log(selectedCheckboxCount);
            const deleteButton = document.querySelector('#delete-button');
            deleteButton.disabled = selectedCheckboxCount <= 0;
        });
    });
}