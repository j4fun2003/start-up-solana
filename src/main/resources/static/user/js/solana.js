
//*========== [CONNECT WALLET] ===========
let publicKey;
let reciverKey;
//1: Auto connect

(async () => {
    await window.phantom.solana.connect();
    publicKey = window.phantom.solana.publicKey.toBase58(); //[1,1,1,1]

})();


//2: Manual connect
const connectWallet = async () => {
    await window.phantom.solana.connect();
    publicKey = window.phantom.solana.publicKey.toBase58(); //[1,1,1,1]
    console.log(publicKey);
    fetch('/savePublicKey', {
        method: 'POST',
        body: JSON.stringify(publicKey),
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            // In thông báo từ máy chủ (ví dụ: "Public Key saved successfully")
        });
   // window.location.reload();
}


//========== [MINT NFT PUBLIC KEY] ==========
const SHYFT_API_KEY = "zwFWThmSUHgrHQu6";

const toTransaction = (encodedTransaction) => solanaWeb3.Transaction.from(Uint8Array.from(atob(encodedTransaction), c => c.charCodeAt(0)));

//https://api.shyft.to/sol/v1
const mintNft = async () => {
    var myHeaders = new Headers();
    var titleNFT = $("#projectName").text();
    var descriptionNFT = $("#description").text();
    myHeaders.append("x-api-key", SHYFT_API_KEY);
    myHeaders.append("Access-Control-Allow-Origin", "*");
    const fileInput = document.querySelector("#fileInput");

    var formdata = new FormData();
    formdata.append("network", "devnet");
    formdata.append("wallet", publicKey);
    formdata.append("name", "Title FPT");
    formdata.append("symbol", "FPL");
    formdata.append("description", "DescriptionFPT");
    formdata.append("attributes", '[{"trait_type":"dev power","value":"over 900"}]');
    formdata.append("external_url", "https://shyft.to");
    formdata.append("max_supply", "1");
    formdata.append("royalty", "5");
    formdata.append("file", fileInput.files[0], "image.png");
    // formdata.append("data", fileInput.files[0]);
    formdata.append("receiver", publicKey);
    formdata.append('service_charge', `{ "receiver": "6Vus5VCXZ6KkQEsZzBQ83NDnM9uumTiCnUgDAPmFsmLw",  "amount": 0.01}`);


    var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: formdata,
        redirect: 'follow'
    };

    fetch("https://api.shyft.to/sol/v1/nft/create_detach", requestOptions)
        .then(async response => {
            let res = await response.json();
            let transaction = toTransaction(res.result.encoded_transaction);

            const signedTransaction = await window.phantom.solana.signTransaction(transaction);
            const connection = new solanaWeb3.Connection("https://api.devnet.solana.com");
            const signature = await connection.sendRawTransaction(signedTransaction.serialize());
            getAll();

            fetch('/saveAddress', {
                method: 'POST',
                body: JSON.stringify(nftAddress),
                headers: {
                    'Content-Type': 'application/json'
                }
            })

            document.getElementById("contact").submit();
        })
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}

//=========== [TRANSFER SOLANA] ==========
const transferSol = async () => {
    var fundingGoalElement = $(".fundingGoal");
    reciverKey = $(".reciverKey").text();

    let textReciverKey = reciverKey.substring(1, reciverKey.length - 1);
    var myHeaders = new Headers();
    myHeaders.append("x-api-key", SHYFT_API_KEY);
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({
        "network": "devnet",
        "from_address": publicKey, //Nguoi gui
        "to_address": textReciverKey, //Nguoi nhan
        "amount": parseFloat(fundingGoalElement.text()),
    });

    var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    fetch("https://api.shyft.to/sol/v1/wallet/send_sol", requestOptions)
        .then(async response => {
            let res = await response.json();
            let transaction = toTransaction(res.result.encoded_transaction);

            const signedTransaction = await window.phantom.solana.signTransaction(transaction);
            const connection = new solanaWeb3.Connection("https://api.devnet.solana.com");
            const signature = await connection.sendRawTransaction(signedTransaction.serialize());

            var projectId = window.location.pathname.split("/").pop();

            $.ajax({
                type: "POST",
                url: "/projects/buy/" + projectId,
                success: function (response) {
                    console.log("TRANSFER SUCCESSFULLY!!!");
                    console.log("AJAX Request Success:", response);
                },
                error: function (xhr) {
                    if (xhr.status === 404) {
                        alert("Project not found");
                    } else if (xhr.status === 401) {
                        alert("User not logged in");
                    } else {
                        alert("An error occurred");
                    }
                }
            });

        })
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}
    const transferNft = async  () => {
        var myHeaders = new Headers();
        myHeaders.append("x-api-key", SHYFT_API_KEY);
        myHeaders.append("Content-Type", "application/json");
        nftAddress = $(".addressNft").text();

        var raw = JSON.stringify({
            // "network": "devnet",
            // "token_address": nftAddress,
            // "from_address": reciverKey,
            // "to_address": publicKey,
            // "transfer_authority": true,
            // "fee_payer": publicKey
            "network": "devnet",
            "token_address": nftAddress,
            "from_address": reciverKey,
            "to_address": publicKey,
            "transfer_authority": true
        });

        var requestOptions = {
            method: 'POST',
            headers: myHeaders,
            body: raw,
            redirect: 'follow'
        };

        fetch("https://api.shyft.to/sol/v1/nft/transfer_detach", requestOptions)
            .then(async response => {
                let res = await response.json();
                let transaction = toTransaction(res.result.encoded_transaction);

                const signedTransaction = await window.phantom.solana.signTransaction(transaction);
                const connection = new solanaWeb3.Connection("https://api.devnet.solana.com");
                const signature = await connection.sendRawTransaction(signedTransaction.serialize());
            })
            .then(result => console.log(result))
            .catch(error => console.log('error', error));
}

var nftAddress = "";
const getAll = async  () => {
    var myHeaders = new Headers();
    myHeaders.append("x-api-key", SHYFT_API_KEY);
    myHeaders.append("Content-Type", "application/json");



    var requestOptions = {
        method: 'GET',
        headers: myHeaders,
        redirect: 'follow'
    };

    fetch("https://api.shyft.to/sol/v1/nft/read_all?network=devnet&&address="+publicKey , requestOptions)
        .then( async  response => response.json())
        .then(result => {console.log(result);
            nftAddress = result.result[0].mint
        console.log(nftAddress)}
        )
        .catch(error => console.log('error', error));
}

