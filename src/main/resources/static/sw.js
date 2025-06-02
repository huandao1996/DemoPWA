// config PWA
self.addEventListener('install', event => {
    event.waitUntil(
        caches.open(staticCacheName)
            .then(cache => {
                return cache.addAll(filesToCache);
            })
    );
});

self.addEventListener('fetch', event => {
    console.log('Fetch event for ', event.request.url);
    event.respondWith(
        caches.match(event.request)
            .then(response => {
                if (response) {
                    console.log('Found ', event.request.url, ' in cache');
                    return response;
                }
                console.log('Network request for ', event.request.url);
                return fetch(event.request).then(response => {
                    return caches.open(staticCacheName).then(cache => {
                        cache.put(event.request.url, response.clone());
                        return response;
                    });
                });
            }).catch(error => {
        })
    );
});


// self.addEventListener("push", function(event) {
//     const data = event.data.json();
//     self.registration.showNotification(data.title, {
//         body: data.body,
//         icon: "http://a.xnimg.cn/wap/apple_icon_.png"
//     });
// });

// self.addEventListener("push", function(event) {
//     let data = { title: "Thông báo", body: "Không có nội dung" };
//
//     try {
//         if (event.data) {
//             data = event.data.json();
//         }
//     } catch (e) {
//         console.error(" Lỗi khi xử lý push data:", e);
//     }
//
//     event.waitUntil(
//         self.registration.showNotification(data.title, {
//             body: data.body,
//             icon: "http://a.xnimg.cn/wap/apple_icon_.png"
//         })
//     );
// });

self.addEventListener("push", function(event) {
    console.log("📦 Push event triggered");

    let data = {
        title: "Thông báo",
        body: "Không có nội dung",
    };

    try {
        if (event.data) {
            const text = event.data.text();
            console.log("Push raw data:", text);

            try {
                const json = JSON.parse(text);
                data.title = json.title || data.title;
                data.body = json.body || data.body;
            } catch (e) {
                console.warn(" Push data không phải JSON. Dùng làm nội dung raw.");
                data.body = text;
            }
        } else {
            console.warn(" Không có event.data trong push event");
        }
    } catch (err) {
        console.error(" Lỗi khi xử lý push event:", err);
    }

    console.warn("thành công");
    event.waitUntil(
        self.registration.showNotification(data.title, {
            body: data.body
        })
    );
});

self.addEventListener('activate', async () => {
    // This will be called only once when the service worker is activated.
    console.log('service worker activate')
});


const filesToCache= [
    '/',
    '/?',
    '/ui2?',
    '/ui3?'
];

const staticCacheName = 'pages-cache';
