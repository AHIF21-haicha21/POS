package at.htlkaindorf.springextended.service;

import at.htlkaindorf.springextended.dto.PublisherDTO;
import at.htlkaindorf.springextended.exception.ResourceNotFoundException;
import at.htlkaindorf.springextended.mapper.PublisherMapper;
import at.htlkaindorf.springextended.pojos.Publisher;
import at.htlkaindorf.springextended.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherService {
    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    public List<PublisherDTO> getAllPublisher() {
        List<Publisher> publisherList = publisherRepository.findAll();

        return publisherList.stream().map(publisherMapper::toDTO).toList();
    }

    public PublisherDTO getPublisherById(Long id) {
        Publisher publisher = publisherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Publisher with ID " + id + " could not be found!"));

        return publisherMapper.toDTO(publisher);

    }

    public PublisherDTO createPublisher(PublisherDTO publisherDto) {
        Publisher publisher = publisherRepository.save(
                publisherMapper.toEntity(publisherDto)
        );

        return publisherMapper.toDTO(publisher);
    }
}
