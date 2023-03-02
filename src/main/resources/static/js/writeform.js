tinymce.init({
    selector: '#editor',
    height: '700px',
    width: '70%'
});

function printEditorContent() {
    let editorContent = tinymce.activeEditor.getContent();

    let printFrame = document.createElement('iframe');
    printFrame.style.display = 'none';
    document.body.appendChild(printFrame);
    printFrame.contentDocument.write(editorContent);
    printFrame.contentWindow.print();
    document.body.removeChild(printFrame);
}

function saveEditorContent() {
    let editorContent = tinymce.activeEditor.getContent();
    console.log(editorContent);

    let modal = document.getElementById("myModal");
    modal.style.display = "block";

    let saveButton = document.getElementById("save-button");
    saveButton.onclick = function() {
        let title = document.getElementById("modal-title").value;
        let memo = document.getElementById("modal-memo").value;
        let folderId = document.getElementById("folderId").value;
        $.ajax({
            type: "POST",
            url: "/document/write",
            data: {
                folderId: folderId,
                editorContent: editorContent,
                title: title,
                memo: memo
            },
            success: function(response) {
                console.log(response);
            },
            error: function(xhr, status, error) {
                console.error(error);
            }
        });
        modal.style.display = "none";
        location.href="/folder/list";
    }

    let closeButton = document.getElementById("close-button");
    closeButton.onclick = function() {
        modal.style.display = "none";
    }
}

function cancelWrite() {
    if (confirm("작성중인 내용이 초기화됩니다. 진행하시겠습니까?")) {
        tinymce.activeEditor.setContent('');
        location.href = '/folder/list';
    }
}

function copyEditorContent() {
    let editorContent = tinymce.activeEditor.getContent({format: 'text'});
    navigator.clipboard.writeText(editorContent)
        .then(() => {
            console.log('Editor content copied to clipboard');
            alert('복사가 완료되었습니다.');
        })
        .catch((error) => {
            console.error('Error copying editor content to clipboard:', error);
        });
}

function spellCheck() {
    let editorContent = tinymce.activeEditor.getContent({format: 'text'});

    const content = {
        content : editorContent
    };

    $.ajax({
        type: "POST",
        url: "http://54.66.159.191:3000/spellcheck",
        data: JSON.stringify(content),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(result) {
            console.log(result)
            let spellCheckTextArea = document.getElementById("spellCheckResult");
            spellCheckTextArea.innerHTML = ''; // 기존 값 초기화
            let suggestions = '';
            for (let i = 0; i < result[0].length; i++) {
                let token = result[0][i].token;
                let sugesstions = result[0][i].suggestions.join(", ");
                suggestions += token + " -> " + sugesstions + "\n\n";
            }
            spellCheckTextArea.innerHTML = suggestions;

        },  error: function() {
            console.log('통신실패!!');
        },
    });
}